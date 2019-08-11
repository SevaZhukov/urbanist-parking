package com.urbanist.parking.feature.report.domain.usecase.transform

import android.graphics.Bitmap

interface TransformRepository {

	fun getBase64StringFromBitmap(bitmap: Bitmap): String
}