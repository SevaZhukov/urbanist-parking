package com.urbanist.parking.feature.report.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import com.urbanist.parking.R
import com.urbanist.parking.databinding.ActivityReportBinding
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.memebattle.memes.mvvm.activity.EventsActivity
import com.urbanist.parking.BR
import com.urbanist.parking.feature.recomendation.RecommendationActivity
import javax.inject.Inject

class ReportActivity : EventsActivity<ActivityReportBinding, ReportViewModel, ReportViewModel.EventsListener>(),
					   ReportViewModel.EventsListener {

	override val eventsListener: ReportViewModel.EventsListener = this

	override val layoutId: Int = R.layout.activity_report

	override val viewModelVariableId: Int = BR.viewModel

	@Inject
	override lateinit var viewModel: ReportViewModel

	private var isLocationEnabled = true
	private var currentLocation = Location(LocationManager.GPS_PROVIDER)
	private var currentPhotoId = 0

	override fun getPhoto(i: Int) {
		openCamera(i)
	}

	override fun getLocation() {
		if (isLocationEnabled.not()) {
			showMessage(getString(R.string.report_error_location))
			return
		}
		viewModel.sendReport(currentLocation)
	}

	override fun showMessage(message: String) {
		Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
	}

	override fun showSuccessMessage() {
		Snackbar.make(binding.root, getString(R.string.report_success), Snackbar.LENGTH_SHORT).show()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		initLocationListener()
	}

	@SuppressLint("MissingPermission")
	private fun initLocationListener() {
		val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
	}

	private val locationListener = object : LocationListener {
		override fun onLocationChanged(location: Location?) {
			currentLocation = location ?: return
		}

		override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

		}

		override fun onProviderEnabled(provider: String?) {
			isLocationEnabled = true
		}

		override fun onProviderDisabled(provider: String?) {
			isLocationEnabled = false
		}
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.recommendation_item -> startActivity(Intent(this, RecommendationActivity::class.java))
		}
		return super.onOptionsItemSelected(item)
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		menuInflater.inflate(R.menu.report_menu, menu)
		return true
	}

	private fun openCamera(numPhoto: Int) {
		currentPhotoId = numPhoto
		val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
		startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST)
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		when (requestCode) {
			CAMERA_PIC_REQUEST -> if (resultCode == Activity.RESULT_OK) {
				val bitmap = data?.extras?.get(DATA_KEY) as Bitmap
				viewModel.setBitmapToImageView(currentPhotoId, bitmap)
			}
		}
	}

	companion object {

		const val CAMERA_PIC_REQUEST = 20000
		const val DATA_KEY = "data"
	}
}