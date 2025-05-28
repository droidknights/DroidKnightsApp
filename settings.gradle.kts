enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

// Gradle Toolchain configuration for automatic provisioning of JBR (JetBrains Runtime) required for Compose Hot Reload
// https://github.com/JetBrains/compose-hot-reload?tab=readme-ov-file#set-up-automatic-provisioning-of-the-jetbrains-runtime-jbr-via-gradle
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

rootProject.name = "DroidKnights"
include(":composeApp", ":backend", ":shared")

// core
include(
    ":core:designsystem",
    ":core:navigation",
    ":core:ui:shader"
)
// core - data
include(
    ":core:data:data-session",
    ":core:data:data-session-api",
    ":core:data:data-setting",
    ":core:data:data-setting-api",
)
// core - datastore
include(
    ":core:datastore:datastore-core",
)
// core - domain
include(
    ":core:domain:domain-session",
    ":core:domain:domain-session-api",
)
// core - model
include(
    ":core:model:model-session",
)
// feature
include(
    ":feature:main",
    ":feature:home",
    ":feature:session",
    ":feature:setting",
    ":feature:contributor",
    ":feature:bookmark",
    ":feature:license"
)
