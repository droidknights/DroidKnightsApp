plugins {
    id("droidknights.feature")
    alias(libs.plugins.aboutlibraries)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
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
