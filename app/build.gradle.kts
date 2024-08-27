plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.navigation.safe.args)
    alias(libs.plugins.ksp)
    id("kotlin-parcelize")
}

android {
    namespace = "com.iti.gofood"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.iti.gofood"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // glide & retrofit
    implementation(libs.bumptech.glide)
    implementation(libs.square.retrofit)
    implementation(libs.square.converter.gson)
    implementation(libs.okhttp3)
    implementation(libs.okhttp3.interceptor)

    // viewModel & nav fragment
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    // Room components
    implementation (libs.androidx.room.runtime)
    ksp (libs.androidx.room.compiler)
    implementation (libs.androidx.room.ktx)
    androidTestImplementation (libs.androidx.room.testing)

    // Kotlin components
    implementation (libs.kotlin.stdlib.jdk7)
    api (libs.kotlinx.coroutines.core)
    api (libs.kotlinx.coroutines.android)

    //Lottie Files
    implementation(libs.lottie.impl)

    // viewPager 2
    implementation(libs.view.pager.impl)
    implementation(libs.circleindicator.impl)

    // youtube player
    implementation (libs.core)

    // read more
    implementation (libs.readmore.textview)


}