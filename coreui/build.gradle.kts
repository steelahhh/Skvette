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
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    composeOptions {
        kotlinCompilerVersion = Dependencies.Compose.kotlinCompilerVersion
        kotlinCompilerExtensionVersion = Dependencies.Compose.version
    }

    defaultConfig {
        minSdkVersion(Config.minSdk)
        targetSdkVersion(Config.targetSdk)
    }
}

dependencies {
    listOf(
        ":core"
    ).forEach {
        implementation(project(it))
    }

    listOf(
        Dependencies.Airbnb.Epoxy.core,
        Dependencies.constraint,
        Dependencies.coreKtx,
        Dependencies.appcompat,
        Dependencies.material,
        Dependencies.Lifecycle.runtime,
        Dependencies.Lifecycle.common,
        Dependencies.Lifecycle.viewModel,
        Dependencies.Lifecycle.livedata,
        Dependencies.Compose.runtime,
        Dependencies.Compose.foundation,
        Dependencies.Compose.ui,
        Dependencies.Compose.layout,
        Dependencies.Compose.material,
        Dependencies.Compose.animation,
        Dependencies.Compose.tooling,
        Dependencies.Compose.livedata,
        Dependencies.Compose.iconsExtended,
        Dependencies.Accompanist.mdcTheme,
        Dependencies.Accompanist.coil
    ).forEach {
        implementation(it)
    }

    listOf(
        Dependencies.Airbnb.Epoxy.compiler
    ).forEach {
        kapt(it)
    }
}
