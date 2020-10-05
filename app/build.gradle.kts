plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.3")
    buildFeatures.viewBinding = true

    defaultConfig {
        applicationId = "com.github.pedrodimoura.statemachinesample"
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions.apply {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    Libs.implementationDependencies().forEach { implementation(it) }
    Libs.kaptDependencies().forEach { kapt(it) }
    Libs.testDependencies().forEach { testImplementation(it) }
    Libs.androidTestDependencies().forEach { androidTestImplementation(it) }
}
