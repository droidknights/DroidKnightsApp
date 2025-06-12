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
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "DroidKnights"
include(":composeApp")

// core
include(
    ":core:designsystem",
    ":core:navigation",
    ":core:ui:shader",
    ":core:testing"
)
// core - data
include(
    ":core:data:data-session",
    ":core:data:data-session-api",
    ":core:data:data-setting",
    ":core:data:data-setting-api",
    ":core:data:data-contributor",
    ":core:data:data-contributor-api"
)
// core - datastore
include(
    ":core:datastore:datastore-core",
    ":core:datastore:datastore-session",
    ":core:datastore:datastore-session-api",
    ":core:datastore:datastore-settings",
    ":core:datastore:datastore-settings-api"
)
// core - domain
include(
    ":core:domain:domain-session",
    ":core:domain:domain-session-api",
    ":core:domain:domain-contributor",
    ":core:domain:domain-contributor-api"
)
// core - model
include(
    ":core:model:model-session",
    ":core:model:model-contributor"
)
// core - network
include(
    ":core:network",
)
// feature
include(
    ":feature:main",
    ":feature:home",
    ":feature:session",
    ":feature:setting",
    ":feature:contributor",
    ":feature:bookmark",
    ":feature:license",
    ":feature:map"
)
