import com.droidknights.app.filterProject

plugins {
    alias(libs.plugins.droidknights.android.application)
    id("com.google.android.gms.oss-licenses-plugin")
    alias(libs.plugins.baselineprofile)
    alias(libs.plugins.roborazzi.plugin)
}

android {
    namespace = "com.droidknights.app"

    defaultConfig {
        applicationId = "com.droidknights.app"
        versionCode = 1
        versionName = "1.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
        }

        create("benchmark") {
            matchingFallbacks.add("release")
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = false
        }
    }
}

dependencies {
    rootProject.subprojects.filterProject {
        if (it.name.contains("baselineprofile")) {
            baselineProfile(it)
        } else if (it.name.contains("testing")) {
            testImplementation(it)
        } else {
            implementation(it)
        }
    }
    implementation(libs.androidx.profileinstaller)
}
