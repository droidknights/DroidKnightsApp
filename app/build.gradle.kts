plugins {
    id("droidknights.android.application")
    id("com.google.android.gms.oss-licenses-plugin")
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
    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

dependencies {
    implementation(projects.core.navigation)
    implementation(projects.feature.bookmark)
    implementation(projects.feature.contributor)
    implementation(projects.feature.home)
    implementation(projects.feature.main)
    implementation(projects.feature.mainNavGraph)
    implementation(projects.feature.session)
    implementation(projects.feature.setting)

    implementation(projects.core.repo.session.impl)
    implementation(projects.core.repo.contributor.impl)
    implementation(projects.core.repo.sponsor.impl)
    implementation(projects.core.repo.setting.impl)

    implementation(projects.core.http)

    implementation(projects.core.designsystem)
}
