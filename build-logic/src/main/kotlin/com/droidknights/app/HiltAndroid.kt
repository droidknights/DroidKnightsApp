package com.droidknights.app

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureHiltAndroid() {
    with(pluginManager) {
        apply("dagger.hilt.android.plugin")
        apply("com.google.devtools.ksp")
    }

    val libs = extensions.libs
    dependencies {
        "implementation"(libs.findLibrary("hilt.android").get())
        "ksp"(libs.findLibrary("hilt.android.compiler").get())
        "kspAndroidTest"(libs.findLibrary("hilt.android.compiler").get())
    }
}

internal class HiltAndroidPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            configureHiltAndroid()
        }
    }
}