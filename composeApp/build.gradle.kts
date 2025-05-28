import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeFeatureFlag
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeHotReload)
    id("droidknights.kotlin.multiplatform")
    id("droidknights.compose.multiplatform")
}

kotlin {
    targets
        .filterIsInstance<KotlinNativeTarget>()
        .forEach { target ->
            target.binaries {
                framework {
                    baseName = "ComposeApp"
                    isStatic = true
                }
            }
        }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        outputModuleName.set("composeApp")
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
            }
        }
        binaries.executable()
    }

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(projects.core.designsystem)

            implementation(projects.core.data.dataSession)
            implementation(projects.core.data.dataSetting)
            implementation(projects.core.data.dataSettingApi)

            implementation(projects.core.domain.domainSession)

            implementation(projects.feature.main)
            implementation(projects.feature.session)
            implementation(projects.feature.setting)
            implementation(projects.feature.license)

            implementation(libs.androidx.lifecycle.runtime.compose)

            implementation(libs.koin.compose.viewmodel.navigation)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}

// Enable Compose Hot Reload optimization
// https://github.com/JetBrains/compose-hot-reload?tab=readme-ov-file#optimization-enable-optimizenonskippinggroups-not-required
composeCompiler {
    featureFlags.add(ComposeFeatureFlag.OptimizeNonSkippingGroups)
}

android {
    namespace = "com.droidknights.app"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.droidknights.app"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "com.droidknights.app.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.droidknights.app"
            packageVersion = "1.0.0"

            macOS {
                dockName = "DroidKnights"
            }
            windows {
                packageName = "DroidKnights"
            }
            linux {
                packageName = "droidknights"
            }
        }
    }
}

// From KotlinConf App
// https://github.com/JetBrains/kotlinconf-app/blob/c81492ee57a8da67390d84ad29f41b08128fe0e1/shared/build.gradle.kts#L193
val buildWebApp by tasks.registering(Copy::class) {
    val wasmDist = "wasmJsBrowserDistribution"

    from(tasks.named(wasmDist).get().outputs.files)

    into(layout.buildDirectory.dir("webApp"))

    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}
