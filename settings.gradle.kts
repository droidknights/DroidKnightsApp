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

    ":core:repo:contributor:api",
    ":core:repo:contributor:impl",
    ":core:repo:setting:api",
    ":core:repo:setting:impl",
    ":core:repo:sponsor:api",
    ":core:repo:sponsor:impl",
    ":core:repo:session:api",
    ":core:repo:session:impl",
    ":core:http",

    ":core:designsystem",
    ":core:navigation",
    ":core:ui",
    ":core:testing",

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
