// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    //safe args
    id("androidx.navigation.safeargs.kotlin") version "2.8.4" apply false
}