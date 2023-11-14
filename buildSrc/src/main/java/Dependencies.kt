/**
 * Created By Khizzar
 * Created On 11/10/2023
 **/

/**
 * Project dependencies, makes it easy to include external binaries or
 * other library modules to build.
 */

object Dependencies {

    const val kotlinCoreKtx = "androidx.core:core-ktx:${BuildDependenciesVersions.coreKtxVersion}"
    const val appCompatVersion =
        "androidx.appcompat:appcompat:${BuildDependenciesVersions.appCompatVersion}"
    const val materialLibVersion =
        "com.google.android.material:material:${BuildDependenciesVersions.materialVersion}"
    const val constraintLayoutVersion =
        "androidx.constraintlayout:constraintlayout:${BuildDependenciesVersions.constraintLayoutVersion}"
    const val vectorVersion =
        "androidx.vectordrawable:vectordrawable:${BuildDependenciesVersions.vectorVersion}"

    /* extensions */
    const val lifeCycleExtVersion =
        "androidx.lifecycle:lifecycle-extensions:${BuildDependenciesVersions.lifeCycleExtVersion}"
    const val liveDataKtxVersion =
        "androidx.lifecycle:lifecycle-livedata-ktx:${BuildDependenciesVersions.liveDataKtxVersion}"
    const val viewModelKtxVersion =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${BuildDependenciesVersions.viewModelKtxVersion}"
    const val navExtVersion =
        "androidx.navigation:navigation-ui-ktx:${BuildDependenciesVersions.navExtVersion}"
    const val navFragmentExtVersion =
        "androidx.navigation:navigation-fragment-ktx:${BuildDependenciesVersions.navExtVersion}"

    /* retrofit */
    const val retrofitVersion =
        "com.squareup.retrofit2:retrofit:${BuildDependenciesVersions.retrofitVersion}"
    const val retrofitConverterVersion =
        "com.squareup.retrofit2:converter-gson:${BuildDependenciesVersions.retrofitVersion}"
    const val retrofitCoroutinesVersion =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${BuildDependenciesVersions.retrofitCoroutinesVersion}"

    /* glide */
    const val glideVersion =
        "com.github.bumptech.glide:glide:${BuildDependenciesVersions.glideVersion}"
    const val glideCompilerVersion =
        "com.github.bumptech.glide:compiler:${BuildDependenciesVersions.glideCompilerVersion}"

    /* coroutines */
    const val coroutineCoreVersion =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${BuildDependenciesVersions.coroutinesVersion}"
    const val coroutinesVersion =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${BuildDependenciesVersions.coroutinesVersion}"

    /* hilt DI */
//    const val hiltVersion =
//        "com.google.dagger:hilt-android:${BuildDependenciesVersions.hiltVersion}"
//    const val hiltKaptVersion =
//        "com.google.dagger:hilt-compiler:${BuildDependenciesVersions.hiltVersion}"
//
//    // When using Kotlin.
//    const val kaptHiltCompiler = "androidx.hilt:hilt-compiler:1.0.0"


        const val hiltVersion =
        "com.google.dagger:hilt-android:${BuildDependenciesVersions.hiltVersion}"
    const val hiltKaptVersion =
        "com.google.dagger:hilt-compiler:${BuildDependenciesVersions.hiltVersion}"

    // When using Kotlin.
    const val kaptHiltCompiler = "androidx.hilt:hilt-compiler:1.0.0"


//    implementation("com.google.dagger:hilt-android:2.44")
//    kapt("com.google.dagger:hilt-android-compiler:2.44")

    /* parser */
    const val gsonVersion = "com.google.code.gson:gson:${BuildDependenciesVersions.gsonVersion}"
    const val retrofitGsonConverterVersion =
        "com.squareup.retrofit2:converter-gson:${BuildDependenciesVersions.retrofitGsonConverterVersion}"
    const val okhttpVersion =
        "com.squareup.okhttp3:okhttp:${BuildDependenciesVersions.okhttpVersion}"
    const val okhttpInterceptorVersion =
        "com.squareup.okhttp3:logging-interceptor:${BuildDependenciesVersions.okhttpVersion}"

    /* WorkManager */
    const val workManagerVersion =
        "androidx.work:work-runtime-ktx:${BuildDependenciesVersions.workVersion}"
    const val hiltWorkVersion =
        "androidx.hilt:hilt-work:${BuildDependenciesVersions.hiltWorkVersion}"

    /* multiDexVersion */
    const val multiDexVersion = "com.android.support:multidex:${BuildDependenciesVersions.multiDex}"

    const val lifeCycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${BuildDependenciesVersions.lifeCycleKtx}"

}