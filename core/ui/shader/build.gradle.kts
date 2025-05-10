plugins {
    alias(libs.plugins.androidLibrary)
    id("droidknights.kotlin.multiplatform")
    id("droidknights.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.foundation)
            implementation(compose.ui)
        }
        androidMain {
            dependencies {
                implementation(compose.ui)
            }
        }

        val nonAndroidMain by creating {
            dependsOn(commonMain.get())
        }

        appleMain {
            dependsOn(nonAndroidMain)
        }

        desktopMain {
            dependsOn(nonAndroidMain)
        }

        wasmJsMain {
            dependsOn(nonAndroidMain)
        }
    }
}

android.namespace = "com.droidknights.app.core.ui.shader"
