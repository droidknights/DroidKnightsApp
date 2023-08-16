plugins {
    id("droidknights.android.application")
}

android {
    namespace = "com.droidknights.app2023"

    defaultConfig {
        applicationId = "com.droidknights.app2023"
        versionCode = 1
        versionName = "1.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.core.navigation)
    implementation(projects.feature.main)
    implementation(projects.feature.home)

    implementation(projects.core.designsystem)
}
