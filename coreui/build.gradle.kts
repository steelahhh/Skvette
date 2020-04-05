plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
    id("com.jakewharton.butterknife")
}

android {
    compileSdkVersion(Config.compileSdk)
    androidExtensions {
        isExperimental = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions.jvmTarget = "1.8"

    defaultConfig {
        minSdkVersion(Config.minSdk)
        targetSdkVersion(Config.targetSdk)
    }
}

dependencies {
    listOf(
        Dependencies.Airbnb.Epoxy.core
    ).forEach {
        implementation(it)
    }

    listOf(
        Dependencies.Airbnb.Epoxy.compiler
    ).forEach {
        kapt(it)
    }
}
