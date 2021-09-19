plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android-extensions")
}
android {

    compileSdk = Versions.compileSdkVersion
    defaultConfig {
        applicationId = "org.anitab.mentorship"
        minSdk = Versions.minSdkVersion
        targetSdk = Versions.targetSdkVersion
        versionCode = Versions.versionCode
        versionName = Versions.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }
    buildFeatures {
        dataBinding = true
        compose = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            manifestPlaceholders["usesCleartextTraffic"] = false
        }
        getByName("debug") {
            manifestPlaceholders["usesCleartextTraffic"] = true
        }
        create("debug_localhost") {
            initWith(getByName("debug"))
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeVersion
    }
}

configurations.all {
    resolutionStrategy {
        this.setForcedModules(setOf(Dependencies.support_annotation))
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Dependencies.kotlin_stdlib)
    implementation(Dependencies.design)
    implementation(Dependencies.constraint_layout)
    implementation(Dependencies.appCompat)
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.test_runner)
    androidTestImplementation(Dependencies.test_rules)
    androidTestImplementation(Dependencies.espresso)
    androidTestImplementation(Dependencies.espresso_intents)
    androidTestImplementation(Dependencies.ext_junit)
    implementation(Dependencies.espresso_idling_resources)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofit_gson_converter)
    implementation(Dependencies.okhttp3_logging_interceptor)

    implementation(Dependencies.lifecycle_viewmodel)
    implementation(Dependencies.app_intro)
    implementation(Dependencies.circule_image_view)

    implementation(Dependencies.fragment_ktx)

    implementation(Dependencies.viewPager2)
    implementation(Dependencies.swipe_refresh_layout)

    implementation(Dependencies.Compose.UI)
    implementation(Dependencies.Compose.Tooling)
    implementation(Dependencies.Compose.Foundation)
    implementation(Dependencies.Compose.Material)
    implementation(Dependencies.Compose.Icons)
    implementation(Dependencies.Compose.IconsExtended)
    implementation(Dependencies.Compose.LiveData)
    implementation(Dependencies.Compose.Activity)
    androidTestImplementation(Dependencies.Compose.Test)
}
