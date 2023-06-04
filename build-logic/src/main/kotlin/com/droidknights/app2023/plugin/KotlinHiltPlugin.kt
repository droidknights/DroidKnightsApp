package com.droidknights.app2023.plugin

import com.droidknights.app2023.configureHiltKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinHiltPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            configureHiltKotlin()
        }
    }
}