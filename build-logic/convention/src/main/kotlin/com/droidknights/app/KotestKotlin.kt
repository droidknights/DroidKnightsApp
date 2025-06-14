package com.droidknights.app

import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

internal fun Project.configureKotest() {
    configureJUnit()

    dependencies {
        "testImplementation"(findLibrary("kotest.runner"))
        "testImplementation"(findLibrary("kotest.assertions"))
    }
}

internal fun Project.configureJUnit() {
    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }
}
