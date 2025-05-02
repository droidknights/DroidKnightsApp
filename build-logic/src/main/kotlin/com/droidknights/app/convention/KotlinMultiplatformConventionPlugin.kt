package com.droidknights.app.convention

import com.droidknights.app.libs
import com.droidknights.app.primitive.KotlinMultiplatformAndroidPlugin
import com.droidknights.app.primitive.KotlinMultiplatformPlugin
import com.droidknights.app.primitive.KotlinMultiplatformiOSPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class KotlinMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("kotlinMultiplatform").get().get().pluginId)
        }

        apply<KotlinMultiplatformPlugin>()
        apply<KotlinMultiplatformAndroidPlugin>()
        apply<KotlinMultiplatformiOSPlugin>()
    }
}