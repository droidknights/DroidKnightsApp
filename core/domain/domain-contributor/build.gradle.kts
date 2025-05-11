import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.library)
}

android {
    setNamespace("core.domain.contributor")
}

dependencies {
    implementation(projects.core.domain.domainContributorApi)

    implementation(projects.core.data.dataContributorApi)

    implementation(libs.inject)
}
