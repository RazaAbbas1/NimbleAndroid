import com.android.build.gradle.internal.scope.ProjectInfo.Companion.getBaseName

plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
//    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_PARCELIZE)
//    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.DAGGER_HILT)
    id(BuildPlugins.KOTLIN_PLUGIN)
    kotlin("kapt")
}

android {
    namespace = BuildAndroidConfig.NETWORK_LIB_NAMESPACE
    compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION

        testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
        consumerProguardFiles("consumer-rules.pro")
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packaging {
        exclude("META-INF/gradle/incremental.annotation.processors") // Exclude the problematic path
        // You can add more exclusions or configurations as needed
    }
}

dependencies {

    implementation(Dependencies.kotlinCoreKtx)
    implementation(Dependencies.appCompatVersion)
    implementation(Dependencies.materialLibVersion)
    
    // Retrofit with Moshi
    implementation(Dependencies.retrofitVersion)
    implementation(Dependencies.retrofitConverterVersion)
    implementation(Dependencies.retrofitCoroutinesVersion)
    implementation(Dependencies.okhttpVersion)
    implementation(Dependencies.okhttpInterceptorVersion)

    implementation(Dependencies.gsonVersion)
    // Coroutines
    implementation(Dependencies.coroutineCoreVersion)
    implementation(Dependencies.coroutinesVersion)
    
    // Hilt
    implementation(Dependencies.hiltVersion)
    kapt(Dependencies.hiltKaptVersion)
    implementation(Dependencies.kaptHiltCompiler)
}

