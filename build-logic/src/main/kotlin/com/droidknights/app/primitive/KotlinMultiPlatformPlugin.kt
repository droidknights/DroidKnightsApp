package com.droidknights.app.primitive

import com.droidknights.app.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiPlatformPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("kotlinMultiplatform").get().get().pluginId)
        }
        extensions.configure<KotlinMultiplatformExtension> {
            tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java) {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_17)
                }
            }

            applyDefaultHierarchyTemplate()

            sourceSets.apply {
                commonMain {
                    dependencies {
                        // implementation(libs.findLibrary("coroutines-core").get())
                        // implementation(libs.findLibrary("kermit").get())
                    }
                }
            }
            tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink>().configureEach {
                notCompatibleWithConfigurationCache("Configuration cache not supported for a system property read at configuration time")
            }
        }
    }
}
