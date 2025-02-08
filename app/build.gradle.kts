plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
}
android {
    namespace = "com.fredprojects.helloworld"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.fredprojects.helloworld"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
composeCompiler {
    reportsDestination = layout.buildDirectory.dir("composeReports")
    metricsDestination = layout.buildDirectory.dir("composeMetrics")
}
dependencies {
    // DI Dagger-Hilt
    implementation(libs.hilt.android)
    ksp(libs.bundles.hilt)
    // default dependencies
    implementation(libs.bundles.androidx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.app)
    //test
    testImplementation(libs.bundles.test)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.bundles.android.test)
    debugImplementation(libs.bundles.compose.debug)

    implementation(project(":core:database"))
    implementation(project(":core:ui"))

    implementation(project(":features:auth"))
    implementation(project(":features:pws"))
    implementation(project(":features:jump"))
    implementation(project(":features:math"))

    implementation(project(":features:clients:domain"))
    implementation(project(":features:clients:data"))
    implementation(project(":features:clients:presentation"))
}