import com.droidknights.app.configureBuildConfig
import com.droidknights.app.setNamespace

plugins {
    alias(libs.plugins.droidknights.android.library)
}

setNamespace("app.config")
configureBuildConfig()

dependencies {
    implementation(projects.appConfig.appConfigApi)
}
