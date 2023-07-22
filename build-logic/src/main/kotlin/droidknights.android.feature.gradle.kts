import com.droidknights.app2023.configureHiltAndroid

plugins {
    id("droidknights.android.library")
    id("droidknights.android.compose")
}

configureHiltAndroid()

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:domain"))
    implementation(project(":core:navigation"))
}
