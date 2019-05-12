package com.urbanist.parking.feature.report

import android.graphics.Bitmap
import android.location.Location
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.urbanist.parking.core.presentation.BaseViewModel
import com.urbanist.parking.feature.report.domain.repository.ReportRepository
import com.urbanist.parking.feature.report.domain.model.Report
import io.reactivex.rxkotlin.addTo
import java.io.ByteArrayOutputStream
import java.util.*
import javax.inject.Inject

class ReportViewModel @Inject constructor(private val reportRepository: ReportRepository) : BaseViewModel() {

	val firstPhoto = MutableLiveData<Bitmap?>()
	val secondPhoto = MutableLiveData<Bitmap?>()
	val thirdPhoto = MutableLiveData<Bitmap?>()

	val comment = MutableLiveData<String>()

	val sendButtonEnable = MediatorLiveData<Boolean>()

	lateinit var eventsListener: EventsListener

	init {
		clearForm()

		sendButtonEnable.apply {
			addSource(firstPhoto) { it != null && this.value ?: false}
			addSource(secondPhoto) { it != null && this.value ?: false}
			addSource(thirdPhoto) { it != null && this.value ?: false}
		}
	}

	fun setBitmapToImageView(currentPhotoId: Int, bitmap: Bitmap) {
		when (currentPhotoId) {
			FIRST_PHOTO_ID -> firstPhoto.value = bitmap
			SECOND_PHOTO_ID -> secondPhoto.value = bitmap
			THIRD_PHOTO_ID -> thirdPhoto.value = bitmap
		}
	}

	fun onPhotoClick(i: Int) {
		eventsListener.getPhoto(i)
	}

	fun onSendReportButtonClick() {
		sendButtonEnable.value = false
		val first64 = bitmapToBase64String(firstPhoto.value ?: return)
		val second64 = bitmapToBase64String(secondPhoto.value ?: return)
		val third64 = bitmapToBase64String(thirdPhoto.value ?: return)
		val images64 = arrayListOf(first64, second64, third64)
		val location = eventsListener.getLocation()
		val report = Report(location.latitude, location.longitude, comment.value.orEmpty(), images64)
		reportRepository.sendReport(report)
			.subscribe(
				{
					clearForm()
					eventsListener.showSuccessMessage()
					sendButtonEnable.value = true
				},
				{
					eventsListener.showMessage(it.localizedMessage)
					sendButtonEnable.value = true
				}
			).addTo(disposables)
	}

	private fun clearForm() {
		firstPhoto.value = null
		secondPhoto.value = null
		thirdPhoto.value = null
		comment.value = ""
	}

	private fun bitmapToBase64String(bitmap: Bitmap): String {
		val baos = ByteArrayOutputStream()
		bitmap.compress(Bitmap.CompressFormat.JPEG, QUALITY_COMPRESS_BITMAP, baos)
		return Arrays.toString(baos.toByteArray())
	}

	fun setEventListener(eventsListener: EventsListener) {
		this.eventsListener = eventsListener
	}

	interface EventsListener {
		fun getPhoto(i: Int)
		fun getLocation(): Location
		fun showMessage(message: String)
		fun showSuccessMessage()
	}

	companion object {
		const val FIRST_PHOTO_ID = 0
		const val SECOND_PHOTO_ID = 1
		const val THIRD_PHOTO_ID = 2

		const val QUALITY_COMPRESS_BITMAP = 100
	}
}