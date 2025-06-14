plugins {
    id("droidknights.feature")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.bookmark)
            implementation(projects.feature.contributor)
            implementation(projects.feature.home)
            implementation(projects.feature.session)
            implementation(projects.feature.setting)
            implementation(projects.feature.license)
            implementation(projects.feature.map)
            implementation(projects.core.router.router)
            implementation(projects.core.router.routerApi)
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
