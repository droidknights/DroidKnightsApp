package com.droidknights.app

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureMock() {
    configureJUnit()

    dependencies {
        "implementation"(findLibrary("coroutines-core"))
        "testImplementation"(findLibrary("turbine"))

        "testImplementation"(findLibrary("test-mockito"))
        "testImplementation"(findLibrary("test-mockito-kotlin"))
        "testImplementation"(findLibrary("test-junit5"))
        "testRuntimeOnly"(findLibrary("test-junit5-engine"))
    }
}
