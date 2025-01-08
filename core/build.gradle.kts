plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlinSerialization)
    kotlin("kapt")
}

android {
    namespace = "com.example.core"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation (libs.converter.kotlinx.serialization)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp)

    implementation(libs.dagger)
    implementation (libs.dagger.android.support)
    implementation (libs.dagger.android)
    kapt (libs.dagger.android.processor)
    kapt(libs.dagger.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}