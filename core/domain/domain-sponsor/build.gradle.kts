import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.library)
}

android {
    setNamespace("core.domain.sponsor")
}

dependencies {
    implementation(projects.core.domain.domainSponsorApi)
    implementation(projects.core.data.dataSponsorApi)

    implementation(libs.inject)
}
