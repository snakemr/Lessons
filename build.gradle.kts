import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "com.example"
version = "1.5.3"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "15"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation("org.jetbrains.compose.desktop:desktop-jvm-windows-x64:1.5.3")
                implementation("org.jetbrains.compose.material3:material3-desktop:1.5.3")
                //implementation(compose.desktop.currentOs)
                implementation("org.xerial:sqlite-jdbc:3.36.0.3")
                implementation("com.squareup.retrofit2:retrofit:2.9.0")
                implementation("com.squareup.retrofit2:converter-gson:2.9.0")
                implementation(files("lib/ComposeLessonsLib-jvm-1.5.jar"))
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            modules("java.instrument", "java.sql", "jdk.unsupported")
            fromFiles("lib/ComposeLessonsLib-jvm-1.5.jar")
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Lessons"
            packageVersion = "1.0.0"
        }
    }
}
