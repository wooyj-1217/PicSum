import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.library")
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.wooyj.picsum.model"
    compileSdk =
        libs.versions.compileSdk
            .get()
            .toInt()

    defaultConfig {
        minSdk =
            libs.versions.minSdk
                .get()
                .toInt()
        targetSdk =
            libs.versions.targetSdk
                .get()
                .toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    buildFeatures {
        buildConfig = false
        resValues = false
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
