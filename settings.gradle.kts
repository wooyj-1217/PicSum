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

rootProject.name = "PicSum"
include(":app")
include(":data")
// include(":data:local")
// include(":data:remote")
include(":domain")
include(":ui")
include(":ui:base")
include(":ui:common")
include(":ui:theme")
include(":model")
include(":feature:list")
include(":feature:setting")
include(":feature:favorite")
include(":feature:favdetail")
include(":feature:detail")
include(":feature:main")
