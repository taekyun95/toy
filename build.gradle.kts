import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.5.30"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    kotlin("plugin.jpa") version "1.7.22"
    id("org.jetbrains.kotlin.kapt") version "1.3.50"
}

group = "me.ktkoo"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

apply(plugin = "kotlin-kapt")

dependencies {
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("ch.qos.logback:logback-classic:1.4.6")
    implementation("com.querydsl:querydsl-jpa")
    implementation("io.github.microutils:kotlin-logging:3.0.5")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("org.apache.commons:commons-lang3:3.9")
    annotationProcessor("com.querydsl:querydsl-apt")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.h2database:h2:2.2.222")

    implementation("org.mapstruct:mapstruct:1.4.2.Final")
    kapt("org.mapstruct:mapstruct-processor:1.4.2.Final")
    kaptTest("org.mapstruct:mapstruct-processor:1.3.0.Final")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
    options.annotationProcessorPath = configurations["annotationProcessor"]
}

tasks.withType<Test> {
    useJUnitPlatform()
    // 테스트 실행 시 'test' 프로파일 활성화
    systemProperty("spring.profiles.active", "test")
}

noArg {
    annotation("jakarta.persistence.Entity")
}
