plugins {
    alias(libs.plugins.androidLibrary)
    id("droidknights.kotlin.multiplatform")
    id("droidknights.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)

            implementation(libs.coil)
            implementation(libs.coil.network)
        }

        appleMain {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
        androidMain {
            dependencies {
                implementation(libs.ktor.client.okhttp)
            }
        }
        jvmMain {
            dependencies {
                implementation(libs.ktor.client.okhttp)
            }
        }
        wasmJsMain {
            dependencies {
                implementation(libs.ktor.client.js)
            }
        }
    }
}

compose.resources {
    publicResClass = true
    // ref. https://www.jetbrains.com/help/kotlin-multiplatform-dev/whats-new-compose-180.html#option-to-change-the-generated-res-class-name
    nameOfResClass = "DesignRes"
}

android.namespace = "com.droidknights.app.core.designsystem"
