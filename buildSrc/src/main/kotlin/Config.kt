object Config {
    const val minSdk = 21
    const val targetSdk = 28
    const val compileSdk = 28
    const val appVersionCode = 10000
    const val appVersionName = "1.0.0"
    const val applicationId = "io.github.steelahhh.skvette"
}

object Versions {
    const val kotlin = "1.3.72"
    const val androidPlugin = "4.2.0-alpha05"
    const val androidx = "1.2.0"
    const val constraint = "2.0.0-beta4"
    const val lifecycle = "2.2.0"

    const val dagger = "2.27"
    const val assisted = "0.5.2"

    const val klock = "1.4.0"

    const val timber = "4.7.1"
    const val chuck = "3.2.0"
    const val retrofit = "2.8.1"
    const val moshi = "1.9.2"
    const val coil = "0.11.0"

    const val simpleStack = "2.3.2"

    const val mvRx = "2.0.0-alpha4"
    const val epoxy = "4.0.0-beta4"

    const val leakCanary = "2.0"

    const val mockk = "1.9.3.kotlin12"
    const val junit = "5.3.1"
    const val testRunner = "1.1.1"
}

object Dependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    const val coreKtx = "androidx.core:core-ktx:${Versions.androidx}"
    const val activity = "androidx.activity:activity-ktx:1.2.0-alpha05"
    const val fragment = "androidx.fragment:fragment-ktx:1.2.4"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.androidx}-beta01"
    const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    const val refresher = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0-rc01"
    const val material = "com.google.android.material:material:1.2.0-alpha06"

    object AndroidX {
        const val process = "androidx.lifecycle:lifecycle-process:${Versions.lifecycle}"
    }

    object Dagger {
        const val core = "com.google.dagger:dagger:${Versions.dagger}"
        const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    }

    object AssistedInject {
        const val annotations = "com.squareup.inject:assisted-inject-annotations-dagger2:${Versions.assisted}"
        const val compiler = "com.squareup.inject:assisted-inject-processor-dagger2:${Versions.assisted}"
    }

    object Lifecycle {
        const val common = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycle}"
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
        const val process = "androidx.lifecycle:lifecycle-process:${Versions.lifecycle}"
    }

    object SimpleStack {
        const val core = "com.github.Zhuinden:simple-stack:${Versions.simpleStack}"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.7"
    }

    object SqlDelight {
        // TODO: add actual deps
    }

    object Retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val moshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    }

    object Coil {
        const val core = "io.coil-kt:coil:${Versions.coil}"
    }

    const val kotlinResult = "com.michael-bull.kotlin-result:kotlin-result:1.1.6"

    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.0.1"

    object Moshi {
        const val core = "com.squareup.moshi:moshi:${Versions.moshi}"
        const val kotlin = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    }

    object Airbnb {
        const val mvRx = "com.airbnb.android:mvrx:${Versions.mvRx}"

        object Epoxy {
            const val core = "com.airbnb.android:epoxy:${Versions.epoxy}"
            const val compiler = "com.airbnb.android:epoxy-processor:${Versions.epoxy}"
        }
    }

    object Chuck {
        const val core = "com.github.ChuckerTeam.Chucker:library:${Versions.chuck}"
        const val noop = "com.github.ChuckerTeam.Chucker:library-no-op:${Versions.chuck}"
    }

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    const val annotations = "javax.annotation:jsr250-api:1.0"

    object LeakCanary {
        const val core = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    }

    const val testRunner = "androidx.test:runner:${Versions.testRunner}"

    object JUnit {
        const val api = "org.junit.jupiter:junit-jupiter-api:${Versions.junit}"
        const val engine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}"

        object Android {
            const val core = "de.mannodermaus.junit5:android-test-core:1.0.0"
            const val runner = "de.mannodermaus.junit5:android-test-runner:1.0.0"
        }
    }

    object Mockk {
        const val core = "io.mockk:mockk:${Versions.mockk}"
        const val android = "io.mockk:mockk-android:${Versions.mockk}"
    }
}
