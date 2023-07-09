package com.droidknights.app2023

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureCoroutineKotlin() {
    val libs = extensions.libs
    dependencies {
        "implementation"(libs.findLibrary("coroutines.core").get())
        "implementation"(libs.findLibrary("coroutines.test").get())
    }
}
