buildscript {
    repositories {
        google()
        jcenter()
        maven(url = "https://plugins.gradle.org/m2/")
        maven(url = "https://dl.bintray.com/kotlin/kotlin-eap/")
    }

    dependencies {
        classpath("com.android.tools.build:gradle:_")
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
    configurations.configureEach {
        resolutionStrategy {
            eachDependency {
                if (requested.group == "org.jetbrains.kotlin") {
                    // kotlin-stdlib-jre7 no longer exists in 1.4, so we force the
                    // kotlin-stdlib module instead
                    if (requested.module.name == "kotlin-stdlib-jre7") {
                        useTarget(Dependencies.kotlin)
                    } else {
                        useVersion(Versions.kotlin)
                    }
                }
            }
        }
    }
    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).configureEach {
        kotlinOptions {
            // Treat all Kotlin warnings as errors
            // allWarningsAsErrors = true

            // Enable experimental coroutines APIs, including Flow
            freeCompilerArgs += "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
            freeCompilerArgs += "-Xopt-in=kotlinx.coroutines.FlowPreview"
            freeCompilerArgs += "-Xopt-in=kotlin.Experimental"
            freeCompilerArgs += "-Xallow-jvm-ir-dependencies"
            freeCompilerArgs += "-Xskip-prerelease-check"
            freeCompilerArgs += "-Xskip-metadata-version-check"
            freeCompilerArgs += "-Xjvm-default=enable"

            // Set JVM target to 1.8
            jvmTarget = "1.8"
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
