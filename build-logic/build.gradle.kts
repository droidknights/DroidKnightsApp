plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.compiler.gradle.plugin)
    compileOnly(libs.compose.gradle.plugin)
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
    }
}