import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.library)
    alias(libs.plugins.droidknights.kotlin.library.serialization)
}

setNamespace("core.data.session.api")

dependencies {
    api(projects.core.model.modelSession)
}
