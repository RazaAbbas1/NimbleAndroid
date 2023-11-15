plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_PARCELIZE)
    id(BuildPlugins.KOTLIN_PLUGIN)
    id(BuildPlugins.DAGGER_HILT)
    kotlin("kapt")
}

android {
    namespace = BuildAndroidConfig.APPLICATION_ID
    compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION
        targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION
        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME

        testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        getByName(BuildTypes.RELEASE) {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            isDebuggable = BuildTypeRelease.isDebuggableEnabled
            isShrinkResources = BuildTypeRelease.isShrinkResourceEnabled
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
//            signingConfig signingConfigs.config
        }
        getByName(BuildTypes.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            isDebuggable = BuildTypeDebug.isDebuggableEnabled
            isShrinkResources = BuildTypeDebug.isShrinkResourceEnabled
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions.add(BuildProductDimensions.DEFAULT)
    productFlavors {
        create("staging") {
            dimension = BuildProductDimensions.DEFAULT
            versionCode = BuildAndroidConfig.VERSION_CODE
            versionName = BuildAndroidConfig.VERSION_NAME
        }
        create("production") {
            dimension = BuildProductDimensions.DEFAULT

//            versionCode BuildAndroidConfig.PLAY_STORE_PRODUCTION_VERSION_CODE

            versionCode = BuildAndroidConfig.PRODUCTION_VERSION_CODE
            versionName = BuildAndroidConfig.VERSION_NAME
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        dataBinding = true
//        compose = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    packaging {
        resources.excludes.add("META-INF/gradle/incremental.annotation.processors")
    }

}

dependencies {

    implementation(Dependencies.kotlinCoreKtx)
    implementation(Dependencies.appCompatVersion)
    implementation(Dependencies.materialLibVersion)
    implementation(Dependencies.constraintLayoutVersion)
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation(Dependencies.lifeCycleExtVersion)
    implementation(Dependencies.liveDataKtxVersion)
    implementation(Dependencies.viewModelKtxVersion)


    implementation(Dependencies.hiltVersion)
    kapt(Dependencies.hiltKaptVersion)

    implementation(Dependencies.retrofitVersion)
    implementation(Dependencies.retrofitConverterVersion)
    implementation(Dependencies.retrofitCoroutinesVersion)
    implementation(Dependencies.okhttpVersion)
    implementation(Dependencies.okhttpInterceptorVersion)

    implementation(Dependencies.gsonVersion)


    // implement the network project
    implementation(project(mapOf("path" to ":network")))

    /*Lottie Android animation*/
    implementation(Dependencies.lottieAndroid)

    implementation("com.tbuonomo:dotsindicator:5.0")
    implementation("com.squareup.picasso:picasso:2.8")
    implementation("com.facebook.shimmer:shimmer:0.1.0@aar")
}