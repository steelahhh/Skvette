import java.io.FileInputStream
import java.util.*

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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    defaultConfig {
        minSdkVersion(Config.minSdk)
        targetSdkVersion(Config.targetSdk)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "APP_ID", "\"${getProperty("local.properties", "app_id")}\"")
    }
}

dependencies {
    listOf(
        ":core"
    ).forEach {
        implementation(project(it))
    }

    listOf(
        Dependencies.kotlin,
        Dependencies.Coroutines.core,
        Dependencies.Coroutines.android,
        Dependencies.Lifecycle.runtime,
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
