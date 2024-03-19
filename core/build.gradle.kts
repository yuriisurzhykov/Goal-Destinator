plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.yuriisurzhykov.goaldestinator.core"
    compileSdk = ProjectProperties.compileSdk

    defaultConfig {
        minSdk = ProjectProperties.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = ProjectProperties.javaSourceCompatibility
        targetCompatibility = ProjectProperties.javaTargetCompatibility
    }
    kotlinOptions {
        jvmTarget = ProjectProperties.kotlinJvmTarget
    }
}

dependencies {

    api("androidx.core:core-ktx:1.10.0")
    api("androidx.appcompat:appcompat:1.6.1")
    api("com.google.android.material:material:1.8.0")
    api("com.google.devtools.ksp:symbol-processing-api:1.8.10-1.0.9")

    val retrofit_version = "2.9.0"
    api("com.squareup.retrofit2:retrofit:$retrofit_version")
    api("com.squareup.retrofit2:converter-gson:$retrofit_version")
    api("com.squareup.okhttp3:okhttp:4.9.3")
    api("com.squareup.okhttp3:logging-interceptor:4.2.1")

    val room_version = "2.6.1"
    api("androidx.room:room-runtime:$room_version")
    api("androidx.room:room-ktx:$room_version")
    ksp("androidx.room:room-compiler:$room_version")

    val androidKtx = "2.6.1"
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:$androidKtx")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}