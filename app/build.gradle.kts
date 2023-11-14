plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_PARCELIZE)
    id(BuildPlugins.KOTLIN_PLUGIN)
//    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.DAGGER_HILT)
//    id(BuildPlugins.KOTLIN_ANDROID)

    kotlin("kapt")
//    id("org.jetbrains.kotlin.android")
//    id("com.google.dagger.hilt.android")
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
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

//    implementation(Dependencies.navFragmentExtVersion)
//    implementation(Dependencies.navExtVersion)
//    implementation(Dependencies.vectorVersion)
//
    implementation(Dependencies.lifeCycleExtVersion)
    implementation(Dependencies.liveDataKtxVersion)
    implementation(Dependencies.viewModelKtxVersion)
//
//    implementation(Dependencies.glideVersion)
//
//    implementation(Dependencies.glideCompilerVersion)
//
//    implementation(Dependencies.coroutineCoreVersion)
//    implementation(Dependencies.coroutinesVersion)

    implementation(Dependencies.hiltVersion)
    kapt(Dependencies.hiltKaptVersion)
//    implementation(Dependencies.kaptHiltCompiler)


//
    implementation(Dependencies.retrofitVersion)
    implementation(Dependencies.retrofitConverterVersion)
    implementation(Dependencies.retrofitCoroutinesVersion)
    implementation(Dependencies.okhttpVersion)
    implementation(Dependencies.okhttpInterceptorVersion)
//
    implementation(Dependencies.gsonVersion)

//
//    implementation(Dependencies.multiDexVersion)
//
//
//    implementation(Dependencies.lifeCycleRuntimeKtx)

    /* WorkManager */
//    implementation(Dependencies.workManagerVersion)
//    implementation(Dependencies.hiltWorkVersion)

    // implement the network project
    implementation(project(mapOf("path" to ":network")))
}