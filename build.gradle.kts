plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.1.0"
    id("org.jetbrains.intellij.platform") version "2.7.2"
}

repositories {
    mavenCentral()
    
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    intellijPlatform {
        create("IC", "2025.2")
    }
}

intellijPlatform {
    pluginConfiguration {
        name = "${rootProject.name}"
        version = "${rootProject.version}"
    }
    
    sandboxContainer = layout.buildDirectory.dir("idea-sandbox")
}

sourceSets {
    main {
        java {
            srcDirs("src/main/kotlin")
            resources.srcDirs("src/main/resources")
        }
    }
}

tasks {
    kotlin {
        jvmToolchain(21)
    }
    
    jar {
        archiveFileName.set("${rootProject.name}-${project.version}.jar")
    }

    patchPluginXml {
        sinceBuild.set("251.0")
    }

    signPlugin {
        password.set(System.getenv("CERTIFICATE_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}

tasks.processResources {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.buildSearchableOptions {
    maxHeapSize = "3048m"
}