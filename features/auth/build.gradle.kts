plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.fredprojects.features.auth"
    compileSdk = 35

    defaultConfig {
        minSdk = 29

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}
composeCompiler {
    reportsDestination = layout.buildDirectory.dir("composeReports")
    metricsDestination = layout.buildDirectory.dir("composeMetrics")
}
dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:ui"))

    implementation(libs.hilt.android)
    ksp(libs.bundles.hilt)
    // default dependencies
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.presentation)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}