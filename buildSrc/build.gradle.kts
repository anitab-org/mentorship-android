plugins {
    `kotlin-dsl`
}

/**
 * After Kotlin 1.2.60, the Kotlin Gradle Plugin driving the kotlin compiler requires extra
 * dependencies that aren't required by Gradle Kotlin DSL scripts alone and aren't embedded into
 * Gradle. See here for more info: https://github.com/gradle/kotlin-dsl/issues/1033
 */
repositories {
    jcenter()
    google()
}
