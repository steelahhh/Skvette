import java.io.FileInputStream
import java.util.Properties

val kotlin_version: String by extra

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
}
apply {
  plugin("kotlin-android")
}

fun getProperty(fileName: String, prop: String): Any? {
    val propsFile = rootProject.file(fileName)
    if (propsFile.exists()) {
        val props = Properties()
        props.load(FileInputStream(propsFile))
        return props[prop]
    }
    return null
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

    buildFeatures {
        viewBinding = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    defaultConfig {
        applicationId = Config.applicationId
        minSdkVersion(Config.minSdk)
        targetSdkVersion(Config.targetSdk)
        versionCode = Config.appVersionCode
        versionName = Config.appVersionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "APP_ID", "\"${getProperty("local.properties", "app_id")}\"")
    }

    packagingOptions {
        exclude("META-INF/LICENSE*")
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    listOf(
        ":core",
        ":coreui",
        ":data",
        ":features:photos"
    ).forEach {
        implementation(project(it))
    }

    listOf(
        Dependencies.kotlin,
        Dependencies.appcompat,
        Dependencies.activity,
        Dependencies.coreKtx,
        Dependencies.constraint,
        Dependencies.refresher,
        Dependencies.material,
        Dependencies.timber,
        Dependencies.Dagger.core,
        Dependencies.Airbnb.mvRx,
        Dependencies.Airbnb.Epoxy.core,
        Dependencies.Lifecycle.common,
        Dependencies.Lifecycle.viewModel,
        Dependencies.SimpleStack.core,
        Dependencies.Coroutines.core,
        Dependencies.Coroutines.android
    ).forEach {
        implementation(it)
    }

    listOf(
        Dependencies.Dagger.compiler,
        Dependencies.Airbnb.Epoxy.compiler,
        Dependencies.Lifecycle.compiler
    ).forEach {
        kapt(it)
    }

    compileOnly(Dependencies.annotations)

    listOf(
        Dependencies.LeakCanary.core
    ).forEach {
        debugImplementation(it)
    }

    androidTestImplementation(Dependencies.testRunner)
}
