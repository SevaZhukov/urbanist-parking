object Versions {
    const val kotlin = "1.3.31"

    const val constraintLayout = "1.1.3"
    const val appCompat = "1.0.2"
    const val recyclerView = "1.0.0"
    const val core = "1.0.2"
    const val exifInterface = "1.0.0"
    const val material = "1.0.0"

    const val dagger = "2.22.1"

    const val rxJava = "2.2.8"
    const val rxAndroid = "2.1.1"

    const val retrofit = "2.5.0"
    const val okHttp = "3.12.1"

    const val target_sdk = 28
    const val compile_sdk = 28
    const val min_sdk = 19
}

object Dependencies {
    const val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.core}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val exif = "androidx.exifinterface:exifinterface:${Versions.exifInterface}"

    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerAndroidProcessor =
        "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitRxAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    const val okHttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
}

object Modules {
    const val app = ":app"

    object Memes {
        const val mvvm = ":memes:mvvm"
        const val benderadapter = ":memes:benderadapter"
        const val domain = ":memes:domain"
        const val utils = ":memes:utils"
        const val databinding = ":memes:databinding"
    }

    val memes = listOf(
        Memes.mvvm,
        Memes.benderadapter,
        Memes.domain,
        Memes.utils,
        Memes.databinding
    )
}