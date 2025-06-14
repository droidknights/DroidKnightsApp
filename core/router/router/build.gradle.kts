plugins {
    id("droidknights.feature")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)

            implementation(projects.core.router.routerApi)
        }
    }
}

android.namespace = "com.droidknights.app.core.router"
