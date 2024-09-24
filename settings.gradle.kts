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
include(":feature:list:domain")
include(":feature:list:ui")
include(":feature:setting")
include(":feature:setting:ui")
include(":feature:setting:domain")
include(":feature:favorite")
include(":feature:favorite:domain")
include(":feature:favorite:ui")
include(":feature:favdetail")
include(":feature:favdetail:ui")
include(":feature:favdetail:domain")
include(":feature:detail")
include(":feature:detail:ui")
include(":feature:detail:domain")
include(":feature:main")
include(":feature:main:ui")
