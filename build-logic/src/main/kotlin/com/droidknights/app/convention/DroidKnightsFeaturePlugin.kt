package com.droidknights.app.convention

import com.droidknights.app.library
import com.droidknights.app.libs
import com.droidknights.app.primitive.DetektPlugin
import com.droidknights.app.primitive.KotlinMultiPlatformAndroidPlugin
import com.droidknights.app.primitive.KotlinMultiPlatformJvmPlugin
import com.droidknights.app.primitive.KotlinMultiPlatformPlugin
import com.droidknights.app.primitive.KotlinMultiPlatformWasmPlugin
import com.droidknights.app.primitive.KotlinMultiPlatformiOSPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import kotlin.apply

class DroidKnightsFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("androidLibrary").get().get().pluginId)
            apply(libs.findPlugin("kotlinMultiplatform").get().get().pluginId)
            apply(libs.findPlugin("composeMultiplatform").get().get().pluginId)
            apply(libs.findPlugin("composeCompiler").get().get().pluginId)
        }

        apply<KotlinMultiPlatformPlugin>()
        apply<KotlinMultiPlatformAndroidPlugin>()
        apply<KotlinMultiPlatformiOSPlugin>()
        apply<KotlinMultiPlatformJvmPlugin>()
        apply<KotlinMultiPlatformWasmPlugin>()
        apply<DetektPlugin>()

        val composeDeps = extensions.getByType<ComposeExtension>().dependencies
        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.apply {
                commonMain {
                    dependencies {
                        implementation(composeDeps.runtime)
                        implementation(composeDeps.foundation)
                        implementation(composeDeps.ui)
                        implementation(composeDeps.components.resources)
                        implementation(composeDeps.components.uiToolingPreview)

                        implementation(project(":core:designsystem"))
                        implementation(project(":core:navigation"))
                        implementation(libs.library("androidx-navigation-compose"))
                        implementation(libs.library("androidx-lifecycle-runtime-compose"))
                        implementation(libs.library("koin-compose-viewmodel-navigation"))
                    }
                }
            }
        }

        dependencies {
            "debugImplementation"(composeDeps.uiTooling)
            "debugImplementation"(composeDeps.preview)
        }
    }
}
