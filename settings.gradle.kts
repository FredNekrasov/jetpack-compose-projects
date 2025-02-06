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

include(":features:fibonacci:api")
include(":features:fibonacci:impl")

include(":features:inequality:api")
include(":features:inequality:impl")

include(":features:pws:domain")
include(":features:pws:data")
include(":features:pws:presentation")

include(":features:jumps:domain")
include(":features:jumps:data")
include(":features:jumps:presentation")

include(":features:auth:domain")
include(":features:auth:data")
include(":features:auth:presentation")
include(":features:math")
