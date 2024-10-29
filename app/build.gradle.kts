import com.android.build.gradle.internal.api.BaseVariantOutputImpl

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "np.com.codebrust.githubbuildactionwithsubmodule"
    compileSdk = 34

    defaultConfig {
        applicationId = "np.com.codebrust.githubbuildactionwithsubmodule"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        applicationVariants.all{
            val variant = this
            this.outputs.forEach {
                (it as BaseVariantOutputImpl).outputFileName =
                    variant.flavorName + "_" + variant.versionName + "_" + variant.versionCode + ".apk"
            }
        }


//        android.applicationVariants.all { variant ->
//            variant.outputs.each { output ->
//                output.outputFileName = new File(variant.flavorName + "-" + versionName + "-" + versionCode + ".apk")
//            }
//        }
    }

    flavorDimensions += "version"
    productFlavors {
        create("demo"){
            versionCode = 1
            versionName = "0.0.0-demo"
        }
        create("free"){
            versionCode = 2
            versionName = "0.0.2-free"
        }
        create("pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(project(":test_submodule"))
    implementation(project(":test_submodule2"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}