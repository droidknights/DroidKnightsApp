plugins {
    id("droidknights.feature")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.data.dataSettingApi)
        }
    }
}

android.namespace = "com.droidknights.app.feature.setting"
