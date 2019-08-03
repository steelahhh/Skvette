object Config {
    const val minSdk = 21
    const val targetSdk = 28
    const val compileSdk = 28
    const val appVersionCode = 10000
    const val appVersionName = "1.0.0"
    const val applicationId = "io.github.steelahhh.skvette"
}

object Versions {
    const val kotlin = "1.3.40"
    const val androidPlugin = "3.5.0-rc02"
    const val androidx = "1.0.2"
    const val constraint = "2.0.0-beta1"
    const val room = "2.1.0-alpha07"
    const val lifecycle = "2.0.0"

    const val dagger = "2.23.2"
    const val klock = "1.4.0"

    const val rxBinding = "3.0.0-alpha2"
    const val rxKotlin = "2.3.0"
    const val rxRelay = "2.1.0"
    const val rxAndroid = "2.1.1"

    const val timber = "5.0.0-SNAPSHOT"
    const val chuck = "3.0.0-alpha2"
    const val retrofit = "2.6.0"
    const val moshi = "1.8.0"
    const val glide = "4.9.0"

    const val mvRx = "1.0.2"
    const val epoxy = "3.7.0"

    const val leakCanary = "2.0-beta-2"

    const val mockk = "1.9.3.kotlin12"
    const val junit = "5.3.1"
    const val testRunner = "1.1.1"
}

object Dependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    const val coreKtx = "androidx.core:core-ktx:1.0.1"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.androidx}"
    const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    const val material = "com.google.android.material:material:1.1.0-alpha07"

    object Dagger {
        const val core = "com.google.dagger:dagger:${Versions.dagger}"
        const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    }

    object RxBinding {
        const val core = "com.jakewharton.rxbinding3:rxbinding-core:${Versions.rxBinding}"
        const val appCompat = "com.jakewharton.rxbinding3:rxbinding-appcompat:${Versions.rxBinding}"
        const val leanBack = "com.jakewharton.rxbinding3:rxbinding-leanback:${Versions.rxBinding}"
        const val recycler = "com.jakewharton.rxbinding3:rxbinding-recyclerview:${Versions.rxBinding}"
        const val viewPager = "com.jakewharton.rxbinding3:rxbinding-viewpager:${Versions.rxBinding}"
        const val material = "com.jakewharton.rxbinding3:rxbinding-material:${Versions.rxBinding}"
    }

    object Lifecycle {
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycle}"
        const val compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    }

    object Rx {
        const val kotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
        const val android = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
        const val relay = "com.jakewharton.rxrelay2:rxrelay:${Versions.rxRelay}"
    }

    object Room {
        const val core = "androidx.room:room-runtime:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
        const val rx = "androidx.room:room-rxjava2:${Versions.room}"
        const val test = "androidx.room:room-testing:${Versions.room}"
    }

    object Retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val moshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
        const val rx = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    }

    object Glide {
        const val core = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }

    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.0.1"

    object Moshi {
        const val core = "com.squareup.moshi:moshi:${Versions.moshi}"
        const val kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
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

    const val timber = "com.jakewharton.timber:timber-android:${Versions.timber}"

    const val annotations = "javax.annotation:jsr250-api:1.0"

    object LeakCanary {
        const val core = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
        const val objectWatcher = "com.squareup.leakcanary:leakcanary-object-watcher-android:${Versions.leakCanary}"
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
