plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.yuriisurzhykov.goaldestinator"
    compileSdk = ProjectProperties.compileSdk

    defaultConfig {
        applicationId = "com.yuriisurzhykov.goaldestinator"
        minSdk = ProjectProperties.minSdk
        targetSdk = ProjectProperties.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    implementation(project(":quotes"))
    implementation(project(":goals-creation"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}