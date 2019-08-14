object Versions {
	val kotlin = "1.3.31"

	val androidx_constraintlayout = "1.1.3"
	val androidx_appcompat = "1.0.2"
	val androidx_recyclerview = "1.0.0"
	val androidx_core = "1.0.2"
	val androidx_cardview = "1.0.0"
	val androidx_exifinterface = "1.0.0"
	val material = "1.0.0"

	val dagger = "2.22.1"

	val rxjava = "2.2.8"
	val rxandroid = "2.1.1"

	val room = "1.1.1"
	val retrofit = "2.5.0"
	val okhttp = "3.12.1"

	val dexter = "5.0.0"

	val target_sdk = 28
	val compile_sdk = 28
	val min_sdk = 19
}

object Dependencies {
	val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

	val appCompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
	val coreKtx = "androidx.core:core-ktx:${Versions.androidx_core}"
	val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraintlayout}"
	val material = "com.google.android.material:material:${Versions.material}"
	val recyclerView = "androidx.recyclerview:recyclerview:${Versions.androidx_recyclerview}"
	val exif = "androidx.exifinterface:exifinterface:${Versions.androidx_exifinterface}"

	val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
	val rxandroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxandroid}"

	val dagger = "com.google.dagger:dagger:${Versions.dagger}"
	val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
	val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
	val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
	val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

	val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
	val retrofitRxAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
	val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

	val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

	val dexter = "com.karumi:dexter:${Versions.dexter}"
}