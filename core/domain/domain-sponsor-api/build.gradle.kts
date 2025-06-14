import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.library)
}

android {
    setNamespace("core.domain.sponsor.api")
}

dependencies {
    api(projects.core.model.modelSponsor)
}
