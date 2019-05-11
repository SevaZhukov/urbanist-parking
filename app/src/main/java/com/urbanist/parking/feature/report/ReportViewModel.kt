package com.urbanist.parking.feature.report

import android.graphics.Bitmap
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.urbanist.parking.core.presentation.BaseViewModel
import javax.inject.Inject

class ReportViewModel @Inject constructor() : BaseViewModel() {

	val firstPhoto = MutableLiveData<Bitmap?>()
	val secondPhoto = MutableLiveData<Bitmap?>()
	val thirdPhoto = MutableLiveData<Bitmap?>()

	val sendButtonEnable = MediatorLiveData<Boolean>()

	lateinit var eventsListener: EventsListener

	init {
		firstPhoto.value = null
		secondPhoto.value = null
		thirdPhoto.value = null

		sendButtonEnable.apply {
			addSource(firstPhoto) { it != null }
			addSource(secondPhoto) { it != null }
			addSource(thirdPhoto) { it != null }
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

	fun setEventListener(eventsListener: EventsListener) {
		this.eventsListener = eventsListener
	}

	interface EventsListener {
		fun getPhoto(i: Int)
	}

	companion object {
		const val FIRST_PHOTO_ID = 0
		const val SECOND_PHOTO_ID = 1
		const val THIRD_PHOTO_ID = 2
	}
}