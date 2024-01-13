// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files

        classpath("com.android.tools.build:gradle:8.2.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.20")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.6")

        // https://dagger.dev/hilt/gradle-setup
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")

        // https://console.firebase.google.com/project/workout-pixel/overview
        // classpath("com.google.gms:google-services:4.4.0")


    }
}

plugins {
    id("com.google.devtools.ksp") version "1.9.20-1.0.14" apply false
}