import org.jlleitschuh.gradle.ktlint.KtlintExtension

// Top-level build file where you can add configuration options common to all sub-projects/modules.
// Task Kotlin 2.0 지원
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    kotlin("jvm") version "2.0.0" // or kotlin("multiplatform") or any other kotlin plugin
    kotlin("plugin.serialization") version "1.9.0" apply false
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
    id("com.google.devtools.ksp") version "2.0.0-1.0.23" apply false
    id("com.google.firebase.crashlytics") version "3.0.2" apply false
    id("com.google.firebase.firebase-perf") version "1.4.2" apply false
}

// Lint
allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    configure<KtlintExtension> {
        version.set("1.3.1")
        outputColorName.set("RED")
        android.set(true)
    }
}

dependencies {
    ktlintRuleset(libs.ktlint)
}

subprojects {
    tasks {
        afterEvaluate {
            val predicate = { task: Task ->
                task.name.startsWith("preBuild") || task.name.startsWith("compile")
            }
            container
                .filter { predicate(it) }
                .forEach {
                    it.dependsOn("ktlintCheck")
                }
        }
    }
}
