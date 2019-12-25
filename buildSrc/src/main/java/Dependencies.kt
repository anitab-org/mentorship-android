/**
 * Contains the versions of the dependencies being used
 */
object Versions {
    const val compileSdkVersion = 28
    const val minSdkVersion = 19
    const val targetSdkVersion = 28
    const val versionCode = 1
    const val versionName = "1"
    const val gradleBuildTool = "3.5.3"
    const val kotlinVersion = "1.3.61"
    const val designSupportLib = "1.0.0"
    const val constraintLayout = "1.1.3"
    const val junit = "4.12"
    const val testRunner = "1.2.0"
    const val espresso = "3.2.0"
    const val rxJava = "2.2.16"
    const val rxAndroid = "2.1.1"
    const val rxKotlin = "2.4.0"
    const val retrofitVersion = "2.7.0"
    const val okHttp3Version = "4.2.2"
    const val archComponents = "2.1.0"
    const val testRule = "1.2.0"
    const val supportAnnotation = "1.1.0"
    const val appCompat = "1.1.0"

}

/**
 * Contains the dependencies being used by the project
 */
object Dependencies {
    const val gradle_build_tool = "com.android.tools.build:gradle:${Versions.gradleBuildTool}"
    const val appCompat= "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
    const val design = "com.google.android.material:material:${Versions.designSupportLib}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val junit = "junit:junit:${Versions.junit}"
    const val test_runner = "androidx.test:runner:${Versions.testRunner}"
    const val test_rules = "androidx.test:rules:${Versions.testRule}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val rx_java = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rx_android = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val rx_kotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofit_gson_converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    const val retrofit_rxjava2_adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}"
    const val okhttp3_logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp3Version}"
    const val lifecycle_extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.archComponents}"
    const val lifecycle_viewmodel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.archComponents}"
    const val support_annotation = "androidx.annotation:annotation:${Versions.supportAnnotation}"
}
