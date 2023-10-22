plugins {
    kotlin("jvm")
    checkstyle
}

java.sourceCompatibility = JavaVersion.VERSION_1_8

dependencies {
    implementation(project(":businessPeople"))
    implementation("javax.inject:javax.inject:1")
    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // tests
    testCompileOnly(libs.junit.jupiter.api)

    testRuntimeOnly(libs.junit.jupiter.engine)
}
