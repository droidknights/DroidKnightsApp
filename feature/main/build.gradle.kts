plugins {
    alias(libs.plugins.androidLibrary)
    id("droidknights.kotlin.multiplatform")
    id("droidknights.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.bookmark)
            implementation(projects.feature.contributor)
            implementation(projects.feature.home)
            implementation(projects.feature.session)
            implementation(projects.feature.setting)
            implementation(projects.feature.map)

            // TODO feature plugin
            implementation(projects.core.designsystem)
            implementation(libs.androidx.navigation.compose)
            implementation(projects.core.navigation)
        }

        wasmJsMain {
            dependencies {
                implementation(libs.kotlinx.immutable)
            }
        }

        val nonWasmJsMain by creating {
            dependsOn(commonMain.get())
        }

        appleMain {
            dependsOn(nonWasmJsMain)
        }

        desktopMain {
            dependsOn(nonWasmJsMain)
        }

        androidMain {
            dependsOn(nonWasmJsMain)
        }
    }
}

android.namespace = "com.droidknights.app.feature.main"
