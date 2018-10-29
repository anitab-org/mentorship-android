/**
 * Contains the versions of the dependencies being used
 */
object Versions {
    const val compileSdkVersion = 28
    const val minSdkVersion = 19
    const val targetSdkVersion = 28
    const val versionCode = 1
    const val versionName = "1"
    const val gradleBuildTool = "3.1.3"
    const val dataBinding = "3.1.3"
    const val kotlinVersion = "1.2.71"
    const val supportLib = "28.0.0"
    const val constraintLayout = "1.1.3"
    const val junit = "4.12"
    const val testRunner = "1.0.2"
    const val espresso = "3.0.2"
    const val rxJava = "2.1.10"
    const val rxAndroid = "2.0.2"
    const val rxKotlin = "2.2.0"
    const val retrofitVersion = "2.3.0"
    const val okHttp3Version = "3.10.0"
    const val archComponents = "1.1.1"
}

/**
 * Contains the dependencies being used by the project
 */
object Dependencies {
    const val gradle_build_tool = "com.android.tools.build:gradle:${Versions.gradleBuildTool}"
    const val databinding = "com.android.databinding:compiler:${Versions.dataBinding}"
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
    const val appcompat_v7 = "com.android.support:appcompat-v7:${Versions.supportLib}"
    const val design = "com.android.support:design:${Versions.supportLib}"
    const val constraint_layout = "com.android.support.constraint:constraint-layout:${Versions.constraintLayout}"
    const val junit = "junit:junit:${Versions.junit}"
    const val test_runner = "com.android.support.test:runner:${Versions.testRunner}"
    const val espresso = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"
    const val rx_java = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rx_android = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val rx_kotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofit_gson_converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    const val retrofit_rxjava2_adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}"
    const val okhttp3_logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp3Version}"
    const val lifecycle_extensions = "android.arch.lifecycle:extensions:${Versions.archComponents}"
    const val lifecycle_viewmodel = "android.arch.lifecycle:viewmodel:${Versions.archComponents}"
}
