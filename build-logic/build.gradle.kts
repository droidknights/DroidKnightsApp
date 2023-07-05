plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.verify.detektPlugin)
}

gradlePlugin {
    plugins {
        register("androidHilt") {
            id = "droidknights.android.hilt"
            implementationClass = "com.droidknights.app2023.HiltAndroidPlugin"
        }
        register("kotlinHilt") {
            id = "droidknights.kotlin.hilt"
            implementationClass = "com.droidknights.app2023.HiltKotlinPlugin"
        }
    }
}
