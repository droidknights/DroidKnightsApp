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

// Baseline Profile
include(":baselineprofile")

// core
include(
    ":core:designsystem",
    ":core:data",
    ":core:data-api",
    ":core:domain",
    ":core:navigation",
    ":core:model",
    ":core:ui",
    ":core:ui-test-hilt-manifest",
    ":core:testing",
    ":core:datastore",
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
