plugins {
	id("com.android.application")
	id("kotlin-android")
	id("kotlin-android-extensions")
	id("com.google.gms.google-services")
	id("kotlin-kapt")
}

android {
	compileSdkVersion(Versions.compile_sdk)
	defaultConfig {
		applicationId = "com.urbanist.parking"
		minSdkVersion(Versions.min_sdk)
		targetSdkVersion(Versions.target_sdk)
		versionCode = Integer.parseInt(project.property("VERSION_CODE") as String)
		versionName = project.property("VERSION_NAME") as String
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}
	buildTypes {
		getByName("release") {
			isMinifyEnabled  = true
			proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
		}
		getByName("release") {
			isMinifyEnabled  = true
			proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
		}
	}
	dataBinding {
		isEnabled = true
	}
}

dependencies {
	implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
	implementation(Dependencies.kotlinStandardLibrary)

	implementation(Dependencies.appCompat)
	implementation(Dependencies.coreKtx)
	implementation(Dependencies.constraintlayout)

	implementation(Dependencies.dexter)
	implementation(Dependencies.material)
	implementation(Dependencies.rxjava)
	implementation(Dependencies.rxandroid)
	implementation(Dependencies.dagger)
	implementation(Dependencies.daggerAndroid)
	implementation(Dependencies.daggerAndroidSupport)
	kapt(Dependencies.daggerCompiler)
	kapt(Dependencies.daggerAndroidProcessor)
	implementation(Dependencies.retrofit)
	implementation(Dependencies.retrofitRxAdapter)
	implementation(Dependencies.retrofitConverterGson)
	implementation(Dependencies.okhttpInterceptor)

	implementation(project(":memes:mvvm"))
	implementation(project(":memes:domain"))
	implementation(project(":memes:utils"))
	implementation(project(":memes:databinding"))
	implementation(project(":memes:benderadapter"))
}
