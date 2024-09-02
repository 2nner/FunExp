plugins {
    id("com.inner.funexp.android.application")
    id("com.inner.funexp.android.application.compose")
    id("com.inner.funexp.android.hilt")
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.inner.funexp"

    defaultConfig {
        applicationId = "com.inner.funexp"
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles("proguard-rules.pro")
        }
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose))
    implementation(libs.bundles.composeBundle)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.pdfviewer.fragment)
    implementation(libs.material)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}