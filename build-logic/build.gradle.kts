plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidCompose") {
            id = "droidknights.android.compose"
            implementationClass = "com.droidknights.app2023.plugin.AndroidComposePlugin"
        }
        register("androidHilt") {
            id = "droidknights.android.hilt"
            implementationClass = "com.droidknights.app2023.plugin.AndroidHiltPlugin"
        }
    }
}
