plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.devtools.ksp")
}

android {
    compileSdkVersion(31)
    defaultConfig {
        applicationId = "com.osacky.kapt.ksp"
        minSdkVersion(24)
    }
}

dependencies {
    implementation(project(":utilities"))
}

