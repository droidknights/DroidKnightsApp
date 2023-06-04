import com.droidknights.app2023.configureCompose

plugins {
    id("droidknights.android.application")
}

android {
    namespace = "com.droidknights.app2023"

    defaultConfig {
        applicationId = "com.droidknights.app2023"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    configureCompose()

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.core)
    implementation(libs.androidx.compose.ui.tooling.preview)

    testImplementation(libs.junit4)

    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.compose.ui.test)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.testManifest)
}