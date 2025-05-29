plugins {
    alias(libs.plugins.androidLibrary)
    id("droidknights.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.koin.core)
        }

        val nonWasmJsMain by creating {
            dependsOn(commonMain.get())
            dependencies {
                implementation(libs.androidx.datastore.core)
                implementation(libs.androidx.datastore.core.okio)
                implementation(libs.okio)
            }
        }

        listOf(androidMain, iosMain, desktopMain).forEach { sourceSet ->
            sourceSet.get().dependsOn(nonWasmJsMain)
        }
    }
}

android.namespace = "com.droidknights.app.core.datastore.core"
