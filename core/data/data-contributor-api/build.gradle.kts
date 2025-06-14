import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.library)
    alias(libs.plugins.droidknights.kotlin.library.serialization)
}

setNamespace("core.data.contributor.api")

dependencies {
    api(projects.core.model.modelContributor)
}
