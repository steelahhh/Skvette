import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
    id("de.mannodermaus.android-junit5")
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

    defaultConfig {
        applicationId = Config.applicationId
        minSdkVersion(Config.minSdk)
        targetSdkVersion(Config.targetSdk)
        versionCode = Config.appVersionCode
        versionName = Config.appVersionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArgument(
            "runnerBuilder",
            "de.mannodermaus.junit5.AndroidJUnit5Builder"
        )
        buildConfigField("String", "APP_ID", "\"${getProperty("local.properties", "app_id")}\"")
    }

    packagingOptions {
        exclude("META-INF/LICENSE*")
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    listOf(
        project(":core"),
        Dependencies.kotlin,
        Dependencies.appcompat,
        Dependencies.coreKtx,
        Dependencies.constraint,
        Dependencies.material,
        Dependencies.Glide.core,
        Dependencies.timber,
        Dependencies.Dagger.core,
        Dependencies.Rx.kotlin,
        Dependencies.Rx.android,
        Dependencies.Rx.relay,
        Dependencies.RxBinding.core,
        Dependencies.RxBinding.appCompat,
        Dependencies.RxBinding.material,
        Dependencies.okHttpLoggingInterceptor,
        Dependencies.Retrofit.core,
        Dependencies.Retrofit.rx,
        Dependencies.Retrofit.moshi,
        Dependencies.Moshi.core,
        Dependencies.Airbnb.mvRx,
        Dependencies.Airbnb.Epoxy.core,
        Dependencies.Lifecycle.viewModel,
        Dependencies.leakSentry
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

    listOf(Dependencies.leakCanary).forEach {
        debugImplementation(it)
    }

    androidTestImplementation(Dependencies.testRunner)

    testImplementation(Dependencies.JUnit.api)
    testRuntimeOnly(Dependencies.JUnit.engine)

    androidTestImplementation(Dependencies.JUnit.api)
    androidTestImplementation(Dependencies.JUnit.Android.core)
    androidTestRuntimeOnly(Dependencies.JUnit.Android.runner)
    androidTestRuntimeOnly(Dependencies.JUnit.engine)
}
