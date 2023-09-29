plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("de.mannodermaus.android-junit5") version ("1.9.3.0")
}

android {
    namespace = "com.hiberus.handh.dialogandtesting"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.hiberus.handh.dialogandtesting"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    testImplementation ("org.mockito.kotlin:mockito-kotlin:5.1.0")
    testImplementation ("org.mockito:mockito-core:5.5.0")
    testImplementation ("io.mockk:mockk-agent:1.13.7")
    testImplementation ("io.mockk:mockk-android:1.13.7")
    
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation ("org.robolectric:robolectric:4.7")
    
    testImplementation ("org.junit.jupiter:junit-jupiter:5.9.3")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.9.3")
    testImplementation ("org.junit.jupiter:junit-jupiter-params:5.9.3")
    
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.9.3")
    
    androidTestImplementation ("io.mockk:mockk-agent:1.13.7")
    androidTestImplementation ("io.mockk:mockk-android:1.13.7")
    androidTestImplementation ("org.mockito:mockito-android:5.5.0")
    
    androidTestImplementation ("org.junit.jupiter:junit-jupiter:5.9.3")
    androidTestImplementation ("org.junit.jupiter:junit-jupiter-api:5.9.3")
    
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    
    // (Optional) If you also have JUnit 4-based tests
    testImplementation ("junit:junit:4.13.2")
    testRuntimeOnly ("org.junit.vintage:junit-vintage-engine:5.9.3")
    
    
    androidTestImplementation("de.mannodermaus.junit5:android-test-compose:1.0.0-SNAPSHOT")
    androidTestImplementation("de.mannodermaus.junit5:android-test-core:1.3.0")
    androidTestRuntimeOnly("de.mannodermaus.junit5:android-test-runner:1.3.0")

    androidTestImplementation (platform("androidx.compose:compose-bom:2023.03.00"))
    testImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation ("androidx.compose.ui:ui-test-manifest")
    debugImplementation ("androidx.compose.ui:ui-tooling")
}