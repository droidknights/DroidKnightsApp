package com.droidknights.app2023.plugin

import com.droidknights.app2023.configureComposeAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidComposePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            configureComposeAndroid()
        }
    }
}
