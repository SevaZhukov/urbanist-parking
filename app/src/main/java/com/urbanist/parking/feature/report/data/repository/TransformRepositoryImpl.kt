package com.urbanist.parking.feature.report.data.repository

import android.graphics.Bitmap
import com.urbanist.parking.feature.report.data.datasource.transform.TransformDataSource
import com.urbanist.parking.feature.report.domain.usecase.transform.TransformRepository
import javax.inject.Inject

class TransformRepositoryImpl @Inject constructor(
	private val transformDataSource: TransformDataSource
) : TransformRepository {

	override fun getBase64StringFromBitmap(bitmap: Bitmap): String = transformDataSource.getBase64StringFromBitmap(bitmap)
}