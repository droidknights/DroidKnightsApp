package com.droidknights.app2023.plugin

import com.droidknights.app2023.configureHiltAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidHiltPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            configureHiltAndroid()
        }
    }
}
