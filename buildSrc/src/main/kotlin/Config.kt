object Config {
  const val minSdk = 21
  const val targetSdk = 28
  const val compileSdk = 28
  const val appVersionCode = 10000
  const val appVersionName = "1.0.0"
  const val applicationId = "io.github.steelahhh.skvette"
}

object Versions {
  const val kotlin = "1.4.10"
}

object Dependencies {
  const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:_"

  const val coreKtx = "androidx.core:core-ktx:_"
  const val activity = "androidx.activity:activity-ktx:_"
  const val fragment = "androidx.fragment:fragment-ktx:_"
  const val appcompat = "androidx.appcompat:appcompat:_"
  const val constraint = "androidx.constraintlayout:constraintlayout:_"
  const val refresher = "androidx.swiperefreshlayout:swiperefreshlayout:_"
  const val material = "com.google.android.material:material:_"

  object Compose {
    const val version = "1.0.0-alpha07"

    const val kotlinCompilerVersion = "1.4.10"

    const val runtime = "androidx.compose.runtime:runtime:_"
    const val livedata = "androidx.compose.runtime:runtime-livedata:_"
    const val savedInstanceState = "androidx.compose.runtime:runtime-saved-instance-state:_"

    const val foundation = "androidx.compose.foundation:foundation:_"
    const val layout = "androidx.compose.foundation:foundation-layout:_"

    const val ui = "androidx.compose.ui:ui:_"
    const val material = "androidx.compose.material:material:_"
    const val iconsExtended = "androidx.compose.material:material-icons-extended:_"

    const val animation = "androidx.compose.animation:animation:_"

    const val tooling = "androidx.ui:ui-tooling:_"
    const val test = "androidx.ui:ui-test:_"
  }

  object Accompanist {
    const val mdcTheme = "com.google.android.material:compose-theme-adapter:_"
    const val coil = "dev.chrisbanes.accompanist:accompanist-coil:_"
  }

  object Dagger {
    const val core = "com.google.dagger:dagger:_"
    const val compiler = "com.google.dagger:dagger-compiler:_"
  }

  object AssistedInject {
    const val annotations = "com.squareup.inject:assisted-inject-annotations-dagger2:_"
    const val compiler = "com.squareup.inject:assisted-inject-processor-dagger2:_"
  }

  object Lifecycle {
    const val common = "androidx.lifecycle:lifecycle-common-java8:_"
    const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:_"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel:_"
    const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:_"
    const val compiler = "androidx.lifecycle:lifecycle-compiler:_"
    const val process = "androidx.lifecycle:lifecycle-process:_"
  }

  object SimpleStack {
    const val core = "com.github.Zhuinden:simple-stack:_"
  }

  object Coroutines {
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:_"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:_"
  }

  object SqlDelight {
    // TODO: add actual deps
  }

  object Retrofit {
    const val core = "com.squareup.retrofit2:retrofit:_"
    const val moshi = "com.squareup.retrofit2:converter-moshi:_"
  }

  object Coil {
    const val core = "io.coil-kt:coil:_"
  }

  const val kotlinResult = "com.michael-bull.kotlin-result:kotlin-result:_"

  const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:_"

  object Moshi {
    const val core = "com.squareup.moshi:moshi:_"
    const val kotlin = "com.squareup.moshi:moshi-kotlin-codegen:_"
  }

  object Airbnb {
    const val mvRx = "com.airbnb.android:mvrx:_"

    object Epoxy {
      const val core = "com.airbnb.android:epoxy:_"
      const val compiler = "com.airbnb.android:epoxy-processor:_"
    }
  }

  object Chuck {
    const val core = "com.github.ChuckerTeam.Chucker:library:_"
    const val noop = "com.github.ChuckerTeam.Chucker:library-no-op:_"
  }

  const val timber = "com.jakewharton.timber:timber:_"

  const val annotations = "javax.annotation:jsr250-api:1.0"

  object LeakCanary {
    const val core = "com.squareup.leakcanary:leakcanary-android:_"
  }

  const val testRunner = "androidx.test:runner:1.1.1"

  object JUnit {
    const val api = "org.junit.jupiter:junit-jupiter-api:5.3.1"
    const val engine = "org.junit.jupiter:junit-jupiter-engine:5.3.1"

    object Android {
      const val core = "de.mannodermaus.junit5:android-test-core:1.0.0"
      const val runner = "de.mannodermaus.junit5:android-test-runner:1.0.0"
    }
  }

  object Mockk {
    const val core = "io.mockk:mockk:1.9.3.kotlin12"
    const val android = "io.mockk:mockk-android:1.9.3.kotlin12"
  }
}
