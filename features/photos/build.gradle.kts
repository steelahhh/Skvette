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
        features = setOf("parcelize")
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    defaultConfig {
        minSdkVersion(Config.minSdk)
        targetSdkVersion(Config.targetSdk)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArgument(
            "runnerBuilder",
            "de.mannodermaus.junit5.AndroidJUnit5Builder"
        )
    }

    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    listOf(
        project(":core"),
        project(":data"),
        project(":coreui"),
        Dependencies.kotlin,
        Dependencies.Rx.kotlin,
        Dependencies.Rx.android,
        Dependencies.Dagger.core,
        Dependencies.kotlin,
        Dependencies.Airbnb.mvRx,
        Dependencies.Airbnb.Epoxy.core,
        Dependencies.Lifecycle.viewModel,
        Dependencies.appcompat,
        Dependencies.coreKtx,
        Dependencies.constraint,
        Dependencies.refresher,
        Dependencies.material,
        Dependencies.Glide.core,
        Dependencies.timber,
        Dependencies.Dagger.core,
        Dependencies.Rx.kotlin,
        Dependencies.Rx.android,
        Dependencies.Rx.relay
    ).forEach {
        implementation(it)
    }

    listOf(
        Dependencies.Glide.compiler,
        Dependencies.Dagger.compiler,
        Dependencies.Airbnb.Epoxy.compiler,
        Dependencies.Lifecycle.compiler
    ).forEach {
        kapt(it)
    }

    compileOnly(Dependencies.annotations)
}
