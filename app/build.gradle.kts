plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.google.secrets.gradle)
}

android {
    namespace = "com.kostyarazboynik.pokemon"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kostyarazboynik.pokemon"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
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
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.valueOf(libs.versions.javaVersion.get())
        targetCompatibility = JavaVersion.valueOf(libs.versions.javaVersion.get())
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }
}

secrets {
    propertiesFileName = "secrets.properties"
    defaultPropertiesFileName = "secrets.default.properties"
}

dependencies {
    implementation(project(":core:dagger"))
    implementation(project(":core:utils"))
    implementation(project(":domain"))
    implementation(project(":features:feature-pokemon-details"))
    implementation(project(":features:feature-pokemon-list"))
    implementation(project(":pokemon-api"))

    implementation(libs.androidx.appcompat)

    // dagger dependency injection pattern
    kapt(libs.dagger.compiler)
    annotationProcessor(libs.dagger.processor)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp)
    debugImplementation(libs.okhttp.logging.interceptor)
}