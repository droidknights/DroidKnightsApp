plugins {
    alias(libs.plugins.androidLibrary)
    id("droidknights.kotlin.multiplatform")
    id("droidknights.compose.multiplatform")
    alias(libs.plugins.aboutlibraries)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.data.dataSettingApi)

            // TODO feature plugin
            implementation(libs.koin.compose.viewmodel.navigation)
            implementation(libs.androidx.navigation.compose)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(projects.core.designsystem)
            implementation(projects.core.navigation)
            implementation(libs.aboutlibraries.core)
            implementation(libs.aboutlibraries.compose.m3)
        }
    }
}

android.namespace = "com.droidknights.app.feature.license"

tasks.register("copyExportedAboutLibraries") {
    dependsOn("exportLibraryDefinitions")

    doLast {
        val variants = listOf("debug", "release")
        val generatedJson = variants
            .map { file("build/generated/aboutLibraries/$it/res/raw/aboutlibraries.json") }
            .firstOrNull { it.exists() }
            ?: throw GradleException("❌ aboutlibraries.json not found ")

        val target = file("src/commonMain/composeResources/files/aboutlibraries.json")
        target.parentFile.mkdirs()
        generatedJson.copyTo(target, overwrite = true)
        println("✅ Copied aboutlibraries.json to commonMain resources")
    }
}

tasks.named("build") {
    dependsOn("copyExportedAboutLibraries")
}

