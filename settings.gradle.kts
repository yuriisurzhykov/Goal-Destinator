pluginManagement {
    repositories {
        google()
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
rootProject.name = "Goal Destinator"
include(":app")
include(":core")
include(":quotes")
include(":core-test")
include(":goals-creation")
include(":ui-theme")
include(":goals-list")
include(":goals-data")
