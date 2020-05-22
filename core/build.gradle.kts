plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(Config.compileSdk)
    androidExtensions {
        features = setOf("parcelize")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions.jvmTarget = "1.8"

    defaultConfig {
        minSdkVersion(Config.minSdk)
        targetSdkVersion(Config.targetSdk)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArgument("runnerBuilder", "de.mannodermaus.junit5.AndroidJUnit5Builder")
    }
}

dependencies {
    listOf(
        Dependencies.kotlin,
        Dependencies.fragment,
        Dependencies.Rx.kotlin,
        Dependencies.Rx.android,
        Dependencies.Dagger.core,
        Dependencies.Airbnb.Epoxy.core,
        Dependencies.Airbnb.mvRx,
        Dependencies.Coroutines.core,
        Dependencies.Coroutines.android
    ).forEach {
        implementation(it)
    }

    listOf(
        Dependencies.Dagger.compiler
    ).forEach {
        kapt(it)
    }

    compileOnly(Dependencies.annotations)
}
