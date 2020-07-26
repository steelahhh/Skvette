val kotlin_version: String by extra
plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
    id("com.jakewharton.butterknife")
}
apply {
    plugin("kotlin-android")
}

android {
    compileSdkVersion(Config.compileSdk)
    androidExtensions {
        features = setOf("parcelize")
    }

    defaultConfig {
        minSdkVersion(Config.minSdk)
        targetSdkVersion(Config.targetSdk)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {
    listOf(
        ":core",
        ":data",
        ":coreui"
    ).forEach {
        implementation(project(it))
    }

    listOf(
        Dependencies.fragment,
        Dependencies.kotlin,
        Dependencies.Dagger.core,
        Dependencies.kotlin,
        Dependencies.Airbnb.mvRx,
        Dependencies.Airbnb.Epoxy.core,
        Dependencies.Lifecycle.viewModel,
        Dependencies.SimpleStack.core,
        Dependencies.appcompat,
        Dependencies.coreKtx,
        Dependencies.constraint,
        Dependencies.refresher,
        Dependencies.material,
        Dependencies.Coil.core,
        Dependencies.timber,
        Dependencies.Dagger.core,
        Dependencies.Coroutines.core,
        Dependencies.Coroutines.android,
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
        Dependencies.Dagger.compiler,
        Dependencies.AssistedInject.compiler,
        Dependencies.Airbnb.Epoxy.compiler,
        Dependencies.Lifecycle.compiler
    ).forEach {
        kapt(it)
    }

    compileOnly(Dependencies.annotations)
    compileOnly(Dependencies.AssistedInject.annotations)
}
