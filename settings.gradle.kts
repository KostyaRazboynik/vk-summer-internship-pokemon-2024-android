pluginManagement {
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
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PokemonApp"
include(":app")
include(":core:dagger")
include(":core:utils")
include(":domain")
include(":features:feature-pokemon-details")
include(":features:feature-pokemon-list")
include(":pokemon-api")
include(":pokemon-data")
