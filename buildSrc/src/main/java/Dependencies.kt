/**
 * Created by murad on 7/26/18.
 */

object Versions {
    val compileSdkVersion = 27
    val minSdkVersion = 19
    val targetSdkVersion = 27
    val versionCode = 1
    val versionName = "1"
    val gradleBuildTool = "3.1.3"
    val kotlinVersion = "1.1.51"
    val supportLib = "27.1.1"
    val constraintLayout = "1.1.2"
    val junit = "4.12"
    val testRunner = "1.0.2"
    val espresso = "3.0.2"
}

object Dependencies {
    val gradle_build_tool = "com.android.tools.build:gradle:${Versions.gradleBuildTool}"
    val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jre7:${Versions.kotlinVersion}"
    val appcompat_v7 = "com.android.support:appcompat-v7:${Versions.supportLib}"
    val constraint_layout = "com.android.support.constraint:constraint-layout:${Versions.constraintLayout}"
    val junit = "junit:junit:${Versions.junit}"
    val test_runner = "com.android.support.test:runner:${Versions.testRunner}"
    val espresso = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"
}
