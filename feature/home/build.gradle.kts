plugins {
    id("droidknights.feature")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.ui.shader)
        }
    }
}

android.namespace = "com.droidknights.app.feature.home"
