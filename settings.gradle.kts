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

rootProject.name = "AndroidDemo1"
include(":app")
include(":testmodule")
include(":viewpage")
include(":jinjie")
include(":jinjie2")
include(":animation")
include(":animation2")
include(":event")
include(":storage")
include(":sqlite")
include(":expandablelistview")
