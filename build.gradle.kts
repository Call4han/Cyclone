plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.7.20"
    id("org.jetbrains.intellij") version "1.13.0"
}

repositories {
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
}


intellij {
    version.set("2022.1.4")

    plugins.set(listOf(/* Plugin Dependencies */))
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
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
    intellij {
        jar {
            archiveFileName.set("Cyclone-${project.version}-BETA.jar")
        }
    }

    patchPluginXml {
        sinceBuild.set("193.0")
        untilBuild.set("223.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
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
