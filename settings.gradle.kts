pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "DroidKnights2023"
include(
    ":app",

    ":core:designsystem",
    ":core:data",
    ":core:data-api",
    ":core:domain",
    ":core:domain-api",
    ":core:navigation",
    ":core:model",
    ":core:ui",
    ":core:testing",
    ":core:datastore",
    ":core:datastore-api",

    ":feature:main",
    ":feature:main-nav-graph",
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
