package com.droidknights.app

import org.gradle.api.Project

fun Project.setNamespace(name: String) {
    androidExtension.apply {
        namespace = "com.droidknights.app.$name"
    }
}
