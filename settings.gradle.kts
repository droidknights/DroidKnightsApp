pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
    }
}

rootProject.name = "DroidKnights"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")

include(
    ":app-config:app-config",
    ":app-config:app-config-api",
)

// Baseline Profile
include(":baselineprofile")

// core
include(
    ":core:network:network",
    ":core:network:network-api",
    ":core:designsystem",
    ":core:navigation",
    ":core:model",
    ":core:ui",
    ":core:ui-test-hilt-manifest",
    ":core:testing",
    ":core:datastore",
)

// core - data
include(
    ":core:data:data",
    ":core:data:data-api",
    ":core:data:data-sponsor",
    ":core:data:data-sponsor-api",
    ":core:data:data-settings",
    ":core:data:data-settings-api",
)

// core - domain
include(
    ":core:domain:domain",
    ":core:domain:domain-sponsor",
    ":core:domain:domain-sponsor-api",
)

// Feature
include(
    ":feature:main",
    ":feature:home",
    ":feature:session",
    ":feature:setting",
    ":feature:contributor",
    ":feature:bookmark",
)

include(
    ":widget"
)
