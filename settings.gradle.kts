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
    plugins {
        kotlin("android") version "1.8.20" // Ajuste a versão conforme necessário
        id("com.android.application") version "7.4.2" // Ajuste a versão conforme necessário
        id("kotlin-kapt") version "1.8.20" // Use o plugin kapt para integração com KSP
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}


rootProject.name = "Agenda de Contatos Compose"
include(":app")
