package com.urbanist.parking.feature.report.presentation

import android.graphics.Bitmap
import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.memebattle.memes.mvvm.eventsdispatcher.EventsDispatcher
import com.memebattle.memes.mvvm.eventsdispatcher.EventsDispatcherOwner
import com.memebattle.memes.mvvm.viewmodel.BaseViewModel
import com.memebattle.memes.utils.ext.addTo
import com.urbanist.parking.feature.report.domain.entity.Report
import com.urbanist.parking.feature.report.domain.usecase.transform.GetBase64StringFromBitmapUseCase
import com.urbanist.parking.feature.report.domain.usecase.report.SendReportUseCase
import javax.inject.Inject

class ReportViewModel @Inject constructor(
	private val sendReportUseCase: SendReportUseCase,
	private val getBase64StringFromBitmapUseCase: GetBase64StringFromBitmapUseCase
) : BaseViewModel(), EventsDispatcherOwner<ReportViewModel.EventsListener> {

	companion object {
		const val FIRST_PHOTO_ID = 0
		const val SECOND_PHOTO_ID = 1
		const val THIRD_PHOTO_ID = 2

		const val DEFAULT_COMMENT_VALUE = ""
	}

	override val eventsDispatcher: EventsDispatcher<EventsListener> = EventsDispatcher()

	val firstPhoto = MutableLiveData<Bitmap?>()
	val secondPhoto = MutableLiveData<Bitmap?>()
	val thirdPhoto = MutableLiveData<Bitmap?>()

	val comment = MutableLiveData<String>()

	val sendButtonEnable = MutableLiveData<Boolean>()

	init {
		clearForm()
		sendButtonEnable.value = false
	}

	private fun clearForm() {
		firstPhoto.value = null
		secondPhoto.value = null
		thirdPhoto.value = null
		comment.value = DEFAULT_COMMENT_VALUE
	}

	fun setBitmapToImageView(currentPhotoId: Int, bitmap: Bitmap) {
		when (currentPhotoId) {
			FIRST_PHOTO_ID -> firstPhoto.value = bitmap
			SECOND_PHOTO_ID -> secondPhoto.value = bitmap
			THIRD_PHOTO_ID -> thirdPhoto.value = bitmap
		}
		sendButtonEnable.value = checkFullFields()
	}

	private fun checkFullFields(): Boolean = firstPhoto.value != null
		&& secondPhoto.value != null
		&& thirdPhoto.value != null

	fun onPhotoClick(i: Int) {
		eventsDispatcher.dispatchEvent { getPhoto(i) }
	}

	fun onSendReportButtonClick() {
		eventsDispatcher.dispatchEvent { getLocation() }
	}

	fun sendReport(location: Location) {
		val first64 = getBase64StringFromBitmapUseCase(firstPhoto.value ?: return)
		val second64 = getBase64StringFromBitmapUseCase(secondPhoto.value ?: return)
		val third64 = getBase64StringFromBitmapUseCase(thirdPhoto.value ?: return)
		val images64 = arrayListOf(first64, second64, third64)
		sendButtonEnable.value = false
		val report = Report(location.latitude, location.longitude, comment.value.orEmpty(), images64)
		sendReportUseCase.invoke(report)
			.subscribe(
				{
					clearForm()
					eventsDispatcher.dispatchEvent { showSuccessMessage() }
				},
				{
					eventsDispatcher.dispatchEvent { showMessage(it.localizedMessage) }
					sendButtonEnable.value = true
				}
			).addTo(compositeDisposable)
	}

	interface EventsListener {
		fun getPhoto(i: Int)
		fun getLocation()
		fun showMessage(message: String)
		fun showSuccessMessage()
	}
}