package com.droidknights.app.primitive

import com.droidknights.app.androidExtension
import com.droidknights.app.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformAndroidPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        androidExtension.apply {
            compileSdk = libs.findVersion("android-compileSdk").get().requiredVersion.toInt()

            extensions.configure<KotlinMultiplatformExtension> {
                androidTarget {
                    compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
                }
            }

            defaultConfig {
                minSdk = libs.findVersion("android.minSdk").get().requiredVersion.toInt()
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }

            sourceSets {
                getByName("main") {
                    assets.srcDirs("src/androidMain/assets")
                    java.srcDirs("src/androidMain/kotlin")
                    res.srcDirs("src/androidMain/res")
                }
                getByName("test") {
                    assets.srcDirs("src/androidUnitTest/assets")
                    java.srcDirs("src/androidUnitTest/kotlin")
                    res.srcDirs("src/androidUnitTest/res")
                }
            }
        }
    }
}
