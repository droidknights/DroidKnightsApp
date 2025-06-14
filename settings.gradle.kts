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
    ":core:router:router",
    ":core:router:router-api",
    ":core:action:action",
    ":core:action:action-api",
    ":core:designsystem",
    ":core:ui",
    ":core:ui-test-hilt-manifest",
    ":core:testing",
)

// core - model
include(
    ":core:model:model-contributor",
    ":core:model:model-session",
    ":core:model:model-sponsor",
)

// core - data
include(
    ":core:data:data-contributor",
    ":core:data:data-contributor-api",
    ":core:data:data-session",
    ":core:data:data-session-api",
    ":core:data:data-sponsor",
    ":core:data:data-sponsor-api",
    ":core:data:data-settings",
    ":core:data:data-settings-api",
)

// core - datastore
include(
    ":core:datastore:datastore-session",
    ":core:datastore:datastore-session-api",
    ":core:datastore:datastore-settings",
    ":core:datastore:datastore-settings-api",
)

// core - domain
include(
    ":core:domain:domain-contributor",
    ":core:domain:domain-contributor-api",
    ":core:domain:domain-session",
    ":core:domain:domain-session-api",
    ":core:domain:domain-sponsor",
    ":core:domain:domain-sponsor-api",
)

// Feature
include(
    ":feature:main",
    ":feature:home",
    ":feature:home-api",
    ":feature:session",
    ":feature:session-api",
    ":feature:setting",
    ":feature:setting-api",
    ":feature:contributor",
    ":feature:contributor-api",
    ":feature:bookmark",
    ":feature:bookmark-api",
)

include(
    ":widget"
)
