package com.urbanist.parking.feature.report.domain.usecase

import android.graphics.Bitmap
import com.urbanist.parking.feature.report.domain.repository.TransformRepository
import javax.inject.Inject

interface GetBase64StringFromBitmapUseCase {

	operator fun invoke(bitmap: Bitmap): String
}

class GetBase64StringFromBitmapUseCaseImpl @Inject constructor(
	private val repository: TransformRepository
) : GetBase64StringFromBitmapUseCase {

	override fun invoke(bitmap: Bitmap): String = repository.getBase64StringFromBitmap(bitmap)
}