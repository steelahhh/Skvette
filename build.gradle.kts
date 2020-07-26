buildscript {
    repositories {
        google()
        jcenter()
        maven(url = "https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.androidPlugin}")
        classpath(kotlin("gradle-plugin", version = Versions.kotlin))
        classpath("com.jakewharton:butterknife-gradle-plugin:10.1.0")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io/")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
    }
}

subprojects {
    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).configureEach {
        kotlinOptions {
            // Treat all Kotlin warnings as errors
            // allWarningsAsErrors = true

            // Enable experimental coroutines APIs, including Flow
            freeCompilerArgs += "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
            freeCompilerArgs += "-Xopt-in=kotlinx.coroutines.FlowPreview"
            freeCompilerArgs += "-Xopt-in=kotlin.Experimental"

            // Set JVM target to 1.8
            jvmTarget = "1.8"
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
