package com.droidknights.app

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureVerifyDetekt() {
    with(pluginManager) {
        apply("io.gitlab.arturbosch.detekt")
    }

    val libs = extensions.libs
    dependencies {
        "detektPlugins"(libs.findLibrary("verify.detektFormatting").get())
    }
}