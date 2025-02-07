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

rootProject.name = "HelloWorld"
include(":app")

include(":core:database")
include(":core:ui")

include(":features:clients:domain")
include(":features:clients:data")
include(":features:clients:presentation")

include(":features:pws:domain")
include(":features:pws:data")
include(":features:pws:presentation")

include(":features:auth:domain")
include(":features:auth:data")
include(":features:auth:presentation")

include(":features:math")
include(":features:jump")
