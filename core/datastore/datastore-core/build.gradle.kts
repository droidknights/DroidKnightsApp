plugins {
    alias(libs.plugins.androidLibrary)
    id("droidknights.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.koin.core)
            }
        }

        val nonWasmJsMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.bundles.datastore)
            }
        }

        val androidMain by getting
        val iosMain by getting
        val desktopMain by getting
        listOf(androidMain, iosMain, desktopMain).forEach { sourceSet ->
            sourceSet.dependsOn(nonWasmJsMain)
        }
    }
}

android.namespace = "com.droidknights.app.core.datastore.core"
