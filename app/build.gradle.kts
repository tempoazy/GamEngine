
plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    
}

android {
    namespace = "com.gamengine"
    compileSdk = 33
    
    defaultConfig {
        applicationId = "com.gamengine"
        minSdk = 25
        targetSdk = 33
        versionCode = 1
        versionName = "@string/app_version_name"
        
        vectorDrawables { 
            useSupportLibrary = true
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
        
    }
    
}

dependencies {
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.squareup.okhttp3:okcurl:5.0.0-alpha.12")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("com.github.bumptech.glide:glide:4.13.2")
    implementation(platform("com.google.firebase:firebase-bom:32.7.1"))
}
