package com.urbanist.parking.feature.report.domain.usecase.transform

import android.graphics.Bitmap
import javax.inject.Inject

interface GetBase64StringFromBitmapUseCase {

	operator fun invoke(bitmap: Bitmap): String
}

class GetBase64StringFromBitmapUseCaseImpl @Inject constructor(
	private val repository: TransformRepository
) : GetBase64StringFromBitmapUseCase {

	override fun invoke(bitmap: Bitmap): String = repository.getBase64StringFromBitmap(bitmap)
}