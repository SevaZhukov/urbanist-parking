package com.urbanist.parking.feature.report.presentation

import android.graphics.Bitmap
import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.urbanist.parking.core.presentation.BaseViewModel
import com.urbanist.parking.feature.report.domain.entity.Report
import com.urbanist.parking.feature.report.domain.usecase.GetBase64StringFromBitmapUseCase
import com.urbanist.parking.feature.report.domain.usecase.SendReportUseCase
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class ReportViewModel @Inject constructor(
	private val sendReportUseCase: SendReportUseCase,
	private val getBase64StringFromBitmapUseCase: GetBase64StringFromBitmapUseCase
) : BaseViewModel() {

	companion object {
		const val FIRST_PHOTO_ID = 0
		const val SECOND_PHOTO_ID = 1
		const val THIRD_PHOTO_ID = 2
	}

	val firstPhoto = MutableLiveData<Bitmap?>()
	val secondPhoto = MutableLiveData<Bitmap?>()
	val thirdPhoto = MutableLiveData<Bitmap?>()

	val comment = MutableLiveData<String>()

	val sendButtonEnable = MutableLiveData<Boolean>()

	lateinit var eventsListener: EventsListener

	init {
		clearForm()
		sendButtonEnable.value = false
	}

	private fun clearForm() {
		firstPhoto.value = null
		secondPhoto.value = null
		thirdPhoto.value = null
		comment.value = ""
	}

	fun setBitmapToImageView(currentPhotoId: Int, bitmap: Bitmap) {
		when (currentPhotoId) {
			FIRST_PHOTO_ID -> firstPhoto.value = bitmap
			SECOND_PHOTO_ID -> secondPhoto.value = bitmap
			THIRD_PHOTO_ID -> thirdPhoto.value = bitmap
		}
		sendButtonEnable.value = checkFullFields()
	}

	private fun checkFullFields(): Boolean {
		return firstPhoto.value != null
			&& secondPhoto.value != null
			&& thirdPhoto.value != null
	}

	fun onPhotoClick(i: Int) {
		eventsListener.getPhoto(i)
	}

	fun onSendReportButtonClick() {
		val first64 = getBase64StringFromBitmapUseCase(firstPhoto.value ?: return)
		val second64 = getBase64StringFromBitmapUseCase(secondPhoto.value ?: return)
		val third64 = getBase64StringFromBitmapUseCase(thirdPhoto.value ?: return)
		val images64 = arrayListOf(first64, second64, third64)
		val location = eventsListener.getLocation() ?: return
		sendButtonEnable.value = false
		val report = Report(location.latitude, location.longitude, comment.value.orEmpty(), images64)
		sendReportUseCase.invoke(report)
			.subscribe(
				{
					clearForm()
					eventsListener.showSuccessMessage()
				},
				{
					eventsListener.showMessage(it.localizedMessage)
					sendButtonEnable.value = true
				}
			).addTo(disposables)
	}

	fun setEventListener(eventsListener: EventsListener) {
		this.eventsListener = eventsListener
	}

	interface EventsListener {
		fun getPhoto(i: Int)
		fun getLocation(): Location?
		fun showMessage(message: String)
		fun showSuccessMessage()
	}
}