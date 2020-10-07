object Libs {
    private const val kotlinGradlePlugin: String =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    private const val kotlinStdLib: String = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    private const val androidGradle: String =
        "com.android.tools.build:gradle:${Versions.androidGradle}"

    private const val androidXExtJUnit: String = "androidx.test.ext:junit:${Versions.extJUnit}"

    private const val jUnit: String = "junit:junit:${Versions.jUnit}"

    private const val constraintlayout: String =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"

    private const val espressoCore: String =
        "androidx.test.espresso:espresso-core:${Versions.espressoCore}"

    private const val appCompat: String = "androidx.appcompat:appcompat:${Versions.appCompat}"

    private const val coreKtx: String = "androidx.core:core-ktx:${Versions.coreKtx}"

    private const val tinderStateMachine: String =
        "com.tinder.statemachine:statemachine:${Versions.stateMachine}"

    private const val lifecycle: String =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    private const val lifecycleCompiler: String =
        "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"

    private const val retrofit: String =
        "com.squareup.retrofit2:retrofit:${Versions.retrofit}"

    private const val gsonConverter: String =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    private const val koinAndroid: String =
        "org.koin:koin-android:${Versions.koin}"

    private const val koinScope: String =
        "org.koin:koin-androidx-scope:${Versions.koin}"

    private const val koinViewModel: String =
        "org.koin:koin-androidx-viewmodel:${Versions.koin}"

    private const val loggingInterceptor: String =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"

    fun classpathDependencies() = listOf(androidGradle, kotlinGradlePlugin)

    fun implementationDependencies() = listOf(
        kotlinStdLib,
        constraintlayout,
        appCompat,
        coreKtx,
        tinderStateMachine,
        lifecycle,
        retrofit,
        gsonConverter,
        koinAndroid,
        koinScope,
        koinViewModel,
        loggingInterceptor
    )

    fun kaptDependencies() = listOf(
        lifecycleCompiler
    )

    fun testDependencies() = listOf(jUnit)

    fun androidTestDependencies() = listOf(espressoCore, androidXExtJUnit)
}
