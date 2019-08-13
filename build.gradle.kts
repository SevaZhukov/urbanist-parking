buildscript {

    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.4.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath("com.google.gms:google-services:4.3.0")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}
