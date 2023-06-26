import com.droidknights.app2023.configureComposeAndroid
import com.droidknights.app2023.configureHiltAndroid

plugins {
    id("droidknights.android.library")
}

configureHiltAndroid()
configureComposeAndroid()

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:domain"))
}
