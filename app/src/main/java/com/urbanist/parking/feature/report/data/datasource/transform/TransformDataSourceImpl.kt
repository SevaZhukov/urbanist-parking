package com.urbanist.parking.feature.report.data.datasource.transform

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

class TransformDataSourceImpl: TransformDataSource {

	private companion object {
		const val QUALITY_COMPRESS_BITMAP = 100
	}

	override fun getBase64StringFromBitmap(bitmap: Bitmap): String {
		val byteArrayOutputStream = ByteArrayOutputStream()
		bitmap.compress(Bitmap.CompressFormat.PNG,
						QUALITY_COMPRESS_BITMAP, byteArrayOutputStream)
		val byteArray = byteArrayOutputStream.toByteArray()
		return Base64.encodeToString(byteArray, Base64.DEFAULT)
	}
}