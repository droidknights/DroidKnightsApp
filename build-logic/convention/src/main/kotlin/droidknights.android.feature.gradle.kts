import com.droidknights.app.configureHiltAndroid
import com.droidknights.app.configureRoborazzi
import com.droidknights.app.findLibrary

plugins {
    id("droidknights.android.library")
    id("droidknights.android.compose")
}

android {
    packaging {
        resources {
            excludes.add("META-INF/**")
        }
    }
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

configureHiltAndroid()
configureRoborazzi()

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:data"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:domain"))
    implementation(project(":core:navigation"))
    implementation(project(":core:ui"))

    testImplementation(project(":core:testing"))

    implementation(findLibrary("hilt.navigation.compose"))
    implementation(findLibrary("androidx.compose.navigation"))
    androidTestImplementation(findLibrary("androidx.compose.navigation.test"))

    implementation(findLibrary("androidx.lifecycle.viewModelCompose"))
    implementation(findLibrary("androidx.lifecycle.runtimeCompose"))
}
