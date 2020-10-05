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

    private const val lifecycleTesting: String =
        "androidx.arch.core:core-testing:${Versions.lifecycle}"

    fun classpathDependencies() = listOf(androidGradle, kotlinGradlePlugin)

    fun implementationDependencies() = listOf(
        kotlinStdLib,
        constraintlayout,
        appCompat,
        coreKtx,
        tinderStateMachine,
        lifecycle
    )

    fun kaptDependencies() = listOf(
        lifecycleCompiler
    )

    fun testDependencies() = listOf(jUnit, lifecycleTesting)

    fun androidTestDependencies() = listOf(espressoCore, androidXExtJUnit)
}
