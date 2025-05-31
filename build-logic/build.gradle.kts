plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.roborazzi.gradle.plugin)
    compileOnly(libs.compose.compiler.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.detekt.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("kmpIos") {
            id = "droidknights.kmp.ios"
            implementationClass = "com.droidknights.app.primitive.KotlinMultiPlatformiOSPlugin"
        }
        register("kmpAndroid") {
            id = "droidknights.kmp.android"
            implementationClass = "com.droidknights.app.primitive.KotlinMultiPlatformAndroidPlugin"
        }
        register("kmpPrimitive") {
            id = "droidknights.kmp"
            implementationClass = "com.droidknights.app.primitive.KotlinMultiPlatformPlugin"
        }
        register("detekt") {
            id = "droidknights.verify.detekt"
            implementationClass = "com.droidknights.app.primitive.DetektPlugin"
        }
        register("kmpConvention") {
            id = "droidknights.kotlin.multiplatform"
            implementationClass =
                "com.droidknights.app.convention.KotlinMultiPlatformConventionPlugin"
        }
        register("cmpConvention") {
            id = "droidknights.compose.multiplatform"
            implementationClass =
                "com.droidknights.app.convention.ComposeMultiPlatformConventionPlugin"
        }
        register("droidknightsFeature") {
            id = "droidknights.feature"
            implementationClass =
                "com.droidknights.app.convention.DroidKnightsFeaturePlugin"
        }
        register("kmpRoborazzi") {
            id = "droidknights.kmp.roborazzi"
            implementationClass = "com.droidknights.app.primitive.KmpRoborazziPlugin"
        }
    }
}