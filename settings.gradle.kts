pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}

plugins {
    id("com.gradle.enterprise") version("3.8.1")
    id("com.gradle.common-custom-user-data-gradle-plugin") version("1.6.3")
}

rootProject.name = "delete-kapt-reproducer"
include("app", "list", "utilities")

gradleEnterprise {
    server = "https://ge.solutions-team.gradle.com"

    buildScan {
        publishAlways()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        google()
    }
}