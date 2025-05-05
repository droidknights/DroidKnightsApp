package com.droidknights.app.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

class KotlinMultiPlatformWasmPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<KotlinMultiplatformExtension> {
                @OptIn(ExperimentalWasmDsl::class)
                wasmJs {
                    browser {
                        val rootDirPath = project.rootDir.path
                        val projectDirPath = project.projectDir.path
                        commonWebpackConfig {
                            devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                                static = (static ?: mutableListOf()).apply {
                                    // Serve sources to debug inside browser
                                    add(rootDirPath)
                                    add(projectDirPath)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}