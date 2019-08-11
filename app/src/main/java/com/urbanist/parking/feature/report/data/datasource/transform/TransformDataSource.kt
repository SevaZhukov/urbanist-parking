package com.urbanist.parking.feature.report.data.datasource.transform

import android.graphics.Bitmap

interface TransformDataSource {

	fun getBase64StringFromBitmap(bitmap: Bitmap): String
}