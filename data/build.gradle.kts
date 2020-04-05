import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
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
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArgument("runnerBuilder", "de.mannodermaus.junit5.AndroidJUnit5Builder")
        buildConfigField("String", "APP_ID", "\"${getProperty("local.properties", "app_id")}\"")
    }
}

dependencies {
    listOf(
        project(":core"),
        Dependencies.kotlin,
        Dependencies.Rx.kotlin,
        Dependencies.Rx.android,
        Dependencies.Dagger.core,
        Dependencies.Moshi.core,
        Dependencies.Chuck.core
    ).forEach {
        implementation(it)
    }

    releaseImplementation(Dependencies.Chuck.noop)

    listOf(
        Dependencies.okHttpLoggingInterceptor,
        Dependencies.Retrofit.core,
        Dependencies.Retrofit.rx,
        Dependencies.Retrofit.moshi
    ).forEach {
        api(it)
    }

    listOf(
        Dependencies.Moshi.kotlin,
        Dependencies.Dagger.compiler
    ).forEach {
        kapt(it)
    }

    compileOnly(Dependencies.annotations)
}
