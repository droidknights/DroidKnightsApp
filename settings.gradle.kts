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
    ":feature:home",
    ":feature:session",
    ":feature:setting",
    ":feature:contributor",
    ":feature:bookmark",
)
