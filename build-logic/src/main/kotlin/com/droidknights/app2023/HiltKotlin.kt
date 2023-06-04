package com.droidknights.app2023

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureHiltKotlin() {
    with(pluginManager) {
        apply("org.jetbrains.kotlin.kapt")
    }

    val libs = extensions.libs
    dependencies {
        "implementation"(libs.findLibrary("hilt.core").get())
        "kapt"(libs.findLibrary("hilt.compiler").get())
    }
}

internal class HiltKotlinPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            configureHiltKotlin()
        }
    }
}