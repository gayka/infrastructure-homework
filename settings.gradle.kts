pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "org.springframework.boot") {
                useModule("org.springframework.boot:spring-boot-gradle-plugin:${requested.version}")
            }
        }
    }
}
dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("springframework", "2.2.1.RELEASE")
            version("springwebframework", "5.2.0.RELEASE")
            version("checkstyle", "8.37")
            library(
                "spring-boot-starter-webflux",
                "org.springframework.boot",
                "spring-boot-starter-webflux"
            ).version("2.2.1.RELEASE")
            library(
                "spring-boot-starter-data-rest",
                "org.springframework.boot",
                "spring-boot-starter-data-rest"
            ).version("2.2.1.RELEASE")
            library(
                "spring-boot-starter-test",
                "org.springframework.boot",
                "spring-boot-starter-test"
            ).version("2.2.1.RELEASE")
            library("spring-boot-starter-data-jpa", "org.springframework.boot", "spring-boot-starter-data-jpa").version(
                "2.2.1.RELEASE"
            )
            library("spring-web", "org.springframework", "spring-web").version("5.2.0.RELEASE")

            library("junit-jupiter-api", "org.junit.jupiter", "junit-jupiter-api").version("5.5.2")
            library("junit-jupiter-engine", "org.junit.jupiter", "junit-jupiter-engine").version("5.5.2")
        }
    }
}
rootProject.name = "people"
include(":app")
include(":businessPeople")
include(":presentation")
include(":useCasePeople")
include(":persistence")
include(":quoteGarden")
include(":avatarsDicebear")
