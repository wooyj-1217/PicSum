import org.jlleitschuh.gradle.ktlint.KtlintExtension

// Top-level build file where you can add configuration options common to all sub-projects/modules.
// Task Kotlin 2.0 지원
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.jvm)
    alias(libs.plugins.serialization) apply false
    alias(libs.plugins.ktlint)
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.firebase.perf) apply false
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
