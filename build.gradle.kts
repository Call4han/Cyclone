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
    // Set the JVM compatibility versions
    kotlin {
        jvmToolchain(17)
    }
    intellij {
        jar {
            archiveFileName.set("Cyclone-${rootProject.version}-BETA.jar")
        }
    }

    patchPluginXml {
        sinceBuild.set("193.0")
        untilBuild.set("231.*")
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