package com.droidknights.app

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureVerifyDetekt() {
    with(pluginManager) {
        apply("io.gitlab.arturbosch.detekt")
    }

    dependencies {
        "detektPlugins"(findLibrary("verify-detektFormatting"))
        "detektPlugins"(findLibrary("verify-twitter-detektComposeRule"))
    }
}
