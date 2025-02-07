plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
}
android {
    namespace = "com.fredprojects.helloworld"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.fredprojects.helloworld"
        minSdk = 29
        targetSdk = 34
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
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
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

    implementation(project(":features:clients:domain"))
    implementation(project(":features:clients:data"))
    implementation(project(":features:clients:presentation"))

    implementation(project(":features:pws:domain"))
    implementation(project(":features:pws:data"))
    implementation(project(":features:pws:presentation"))

    implementation(project(":features:auth:domain"))
    implementation(project(":features:auth:data"))
    implementation(project(":features:auth:presentation"))
}