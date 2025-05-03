package com.droidknights.app.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiPlatformPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        extensions.configure<KotlinMultiplatformExtension> {
            jvmToolchain(17)
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
            }
            applyDefaultHierarchyTemplate()
            androidTarget()

            sourceSets.apply {
                commonMain {
                    dependencies {
                        // implementation(libs.findLibrary("coroutines-core").get())
                        // implementation(libs.findLibrary("kermit").get())
                    }
                }
                androidMain {
                    dependencies {
                        // implementation(libs.findLibrary("coroutines-android").get())
                    }
                }
            }
            tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink>().configureEach {
                notCompatibleWithConfigurationCache("Configuration cache not supported for a system property read at configuration time")
            }
        }
    }
}
