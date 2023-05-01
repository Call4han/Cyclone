plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.8.21"
    id("org.jetbrains.intellij") version "1.13.3"
}

repositories {
    mavenCentral()
}


intellij {
    version.set("2023.1")

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
    kotlin {
        jvmToolchain(17)
    }
    intellij {
        jar {
            archiveFileName.set("Cyclone-${rootProject.version}-BETA.jar")
        }
    }

    patchPluginXml {
        sinceBuild.set("231.0")
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