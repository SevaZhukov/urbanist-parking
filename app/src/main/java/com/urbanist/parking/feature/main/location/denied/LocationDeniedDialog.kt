package com.urbanist.parking.feature.main.location.denied

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.urbanist.parking.R
import kotlinx.android.synthetic.main.dialog_denied_permission_loc.*

class LocationDeniedDialog(context: Context) : Dialog(context) {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		requestWindowFeature(Window.FEATURE_NO_TITLE)
		setContentView(R.layout.dialog_denied_permission_loc)
		button.setOnClickListener {
			dismiss()
		}
	}
}