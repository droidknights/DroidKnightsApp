plugins {
    id("droidknights.feature")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.domain.domainSession)
            implementation(projects.core.domain.domainSessionApi)
            implementation(projects.core.model.modelSession)
            implementation(libs.kotlinx.immutable)
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

android.namespace = "com.droidknights.app.feature.bookmark"
