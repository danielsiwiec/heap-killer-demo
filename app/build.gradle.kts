plugins {
    application
    kotlin("jvm") version "1.7.21"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-api:2.0.3")
    implementation("ch.qos.logback:logback-classic:1.4.4")
    implementation("ch.qos.logback:logback-core:1.4.4")
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

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}
