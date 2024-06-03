pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "DroidKnights"
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
