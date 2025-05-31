package com.droidknights.app.convention

import com.droidknights.app.library
import com.droidknights.app.libs
import com.droidknights.app.primitive.DetektPlugin
import com.droidknights.app.primitive.KmpRoborazziPlugin
import com.droidknights.app.primitive.KotlinMultiPlatformAndroidPlugin
import com.droidknights.app.primitive.KotlinMultiPlatformJvmPlugin
import com.droidknights.app.primitive.KotlinMultiPlatformPlugin
import com.droidknights.app.primitive.KotlinMultiPlatformWasmPlugin
import com.droidknights.app.primitive.KotlinMultiPlatformiOSPlugin
import com.droidknights.app.primitive.composeMultiplatformDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

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
        apply<KmpRoborazziPlugin>()

        composeMultiplatformDependencies()

        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.apply {
                commonMain {
                    dependencies {
                        implementation(project(":core:designsystem"))
                        implementation(project(":core:navigation"))
                        implementation(libs.library("androidx-navigation-compose"))
                        implementation(libs.library("androidx-lifecycle-runtime-compose"))
                        implementation(libs.library("koin-compose-viewmodel-navigation"))
                    }
                }
                
            }
        }
    }
}
