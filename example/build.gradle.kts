plugins {
    id("com.android.application")
    id("com.google.devtools.ksp")
    id("kotlin-android")
}

android {
    compileSdk = 34

    defaultConfig {
        namespace = "ch.karimattia.composechatbotframework"
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
}

dependencies {

    implementation("androidx.compose.material3:material3:1.1.2")
    implementation ("androidx.activity:activity-compose:1.8.2")

    // Integration with ViewModels
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    implementation (project(":Compose_Chatbot_Framework"))
}