import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.util.capitalizeDecapitalize.toUpperCaseAsciiOnly

plugins {
    java
    id("org.springframework.boot") version "2.2.1.RELEASE" apply false
    id("io.spring.dependency-management") version "1.0.8.RELEASE" apply false
    kotlin("jvm") version "1.5.10" apply false
    kotlin("plugin.spring") version "1.5.10" apply false
    id("org.jetbrains.kotlin.plugin.jpa") version "1.5.10" apply false
    id("com.github.ben-manes.versions") version "0.48.0"
    id("org.jlleitschuh.gradle.ktlint") version "11.6.0"
    // id("org.sonarqube") version "4.4.1.3373"
}

allprojects {
    group = "com.stringconcat"
    version = "0.0.1-SNAPSHOT"

    repositories {
        google()
        mavenCentral()
        jcenter()
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
        maven { url = uri("https://dl.bintray.com/kotlin/kotlinx/") }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
            allWarningsAsErrors = true
        }
    }

    tasks.register("checkForSnapshot") {
        group = "verification"
        description = "Check whether there are any SNAPSHOT dependencies."
        doLast {
            val violations = project.configurations
                .filter { it.isCanBeResolved }
                .flatMap { configuration ->
                    configuration.resolvedConfiguration.resolvedArtifacts
                }
                .map { it.moduleVersion.id }
                .filter { !it.toString().contains("stringconcat") }
                .filter { it.version.endsWith("-SNAPSHOT") }
                .toSet()
            if (violations.isNotEmpty()) {
                error("Snapshot dependencies found for project = $project :\n\n${violations.joinToString(separator = "\n")}")
            }
        }
    }

    configurations {
        register("bom")
        register("upToDate")
        register("exceedLatest")
        register("platform")
        register("upgradesFound")
        register("upgradesFound2")
        register("unresolvable")
        register("unresolvable2")
    }

    fun isNonStable(version: String): Boolean {
        val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCaseAsciiOnly().contains(it) }
        val regex = "^[0-9,.v-]+(-r)?$".toRegex()
        val isStable = stableKeyword || regex.matches(version)
        return isStable.not()
    }
}
subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint") // Version should be inherited from parent
    apply(plugin = "com.github.ben-manes.versions")
    repositories {
        // Required to download KtLint
        mavenCentral()
    }

    // Optionally configure plugin
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
    }
}

java.sourceCompatibility = JavaVersion.VERSION_1_8
tasks.build { dependsOn(tasks.dependencyUpdates) }
dependencies {
    implementation(project(":app"))
    implementation(project(":presentation"))
    implementation(project(":persistence"))
    implementation(project(":useCasePeople"))
    implementation(project(":businessPeople"))
    implementation(project(":quoteGarden"))
    implementation(project(":avatarsDicebear"))
}
