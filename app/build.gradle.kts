plugins {
    application
    kotlin("jvm") version "1.7.21"
    kotlin("plugin.spring") version "1.7.21"
    id("org.springframework.boot") version "2.7.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("ch.qos.logback:logback-classic:1.2.9")
    implementation("ch.qos.logback:logback-core:1.2.9")
}

application {
    mainClass.set("com.dansiwiec.HeapDestroyerKt")
}

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "com.dansiwiec.HeapDestroyerKt"
        )
    }
}
