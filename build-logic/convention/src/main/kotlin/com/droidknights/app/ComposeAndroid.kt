package com.droidknights.app

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

internal fun Project.configureComposeAndroid() {
    with(plugins) {
        apply("org.jetbrains.kotlin.plugin.compose")
    }

    androidExtension.apply {
        dependencies {
            val bom = findLibrary("androidx-compose-bom")
            add("implementation", platform(bom))
            add("androidTestImplementation", platform(bom))

            add("implementation", findLibrary("androidx.compose.material3"))
            add("implementation", findLibrary("androidx.compose.ui"))
            add("implementation", findLibrary("androidx.compose.ui.tooling.preview"))
            add("androidTestImplementation", findLibrary("androidx.test.ext"))
            add("androidTestImplementation", findLibrary("androidx.test.espresso.core"))
            add("androidTestImplementation", findLibrary("androidx.compose.ui.test"))
            add("debugImplementation", findLibrary("androidx.compose.ui.tooling"))
            add("debugImplementation", findLibrary("androidx.compose.ui.testManifest"))
        }
    }

    extensions.getByType<ComposeCompilerGradlePluginExtension>().apply {
        includeSourceInformation.set(true)
    }
}

/**
 * Compose Library
 */
fun Project.configureComposeFeature() {
    androidExtension.apply {
        with(plugins) {
            apply("org.jetbrains.kotlin.plugin.compose")
        }
    }
}
