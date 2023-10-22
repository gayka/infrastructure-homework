import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    java
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.jetbrains.kotlin.plugin.jpa")
    id("com.github.ben-manes.versions")
    checkstyle
}

java.sourceCompatibility = JavaVersion.VERSION_1_8
val developmentOnly by configurations.creating
configurations {
    runtimeClasspath {
        extendsFrom(developmentOnly)
    }
}

dependencies {
    // spring modules
    implementation(libs.spring.boot.starter.webflux)
    implementation(libs.spring.boot.starter.data.rest)
    implementation(libs.spring.boot.starter.data.jpa)
    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation(project(":presentation"))
    implementation(project(":persistence"))
    implementation(project(":useCasePeople"))
    implementation(project(":businessPeople"))
    implementation(project(":quoteGarden"))
    implementation(project(":avatarsDicebear"))
    // dev tools
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    // persistance
    implementation("org.postgresql:postgresql:42.3.4")
    implementation("org.liquibase:liquibase-core:4.9.1")
    implementation("com.h2database:h2:2.1.212")
    // tests
    testCompileOnly(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testImplementation(libs.spring.boot.starter.test) {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("io.projectreactor:reactor-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events(TestLogEvent.PASSED, TestLogEvent.FAILED, TestLogEvent.SKIPPED)
        showStandardStreams = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}
