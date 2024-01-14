plugins {
    id("com.android.library")
    id("com.google.devtools.ksp")
    id("kotlin-android")
}

android {
    compileSdk = 34

    defaultConfig {
        // https://jitpack.io/#com.github.karim-attia/ChatbotComposeFramework
        namespace = "ch.karimattia.compose_chatbot_framework"
        minSdk = 28
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
}

dependencies {

    implementation("androidx.compose.material3:material3:1.1.2")

    // Integration with ViewModels
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // Integration with observables
    implementation ("androidx.compose.runtime:runtime-livedata:1.5.4")
}