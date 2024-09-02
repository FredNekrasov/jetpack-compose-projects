plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.fredprojects.features.jumps.presentation"
    compileSdk = 34

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
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
}
dependencies {
    implementation(project(":core:ui"))
    implementation(project(":features:jumps:domain"))
    // DI Dagger-Hilt
    implementation(libs.hilt.android)
    ksp(libs.bundles.hilt)
    // default dependencies
    implementation(libs.bundles.androidx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.presentation)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}