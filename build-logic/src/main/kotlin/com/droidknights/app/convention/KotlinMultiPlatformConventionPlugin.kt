package com.droidknights.app.convention

import com.droidknights.app.libs
import com.droidknights.app.primitive.KotlinMultiPlatformAndroidPlugin
import com.droidknights.app.primitive.KotlinMultiPlatformPlugin
import com.droidknights.app.primitive.KotlinMultiPlatformWasmPlugin
import com.droidknights.app.primitive.KotlinMultiPlatformiOSPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class KotlinMultiPlatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("kotlinMultiplatform").get().get().pluginId)
        }

        apply<KotlinMultiPlatformPlugin>()
        apply<KotlinMultiPlatformAndroidPlugin>()
        apply<KotlinMultiPlatformiOSPlugin>()
        apply<KotlinMultiPlatformWasmPlugin>()
    }
}