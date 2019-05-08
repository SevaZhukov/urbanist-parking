package com.urbanist.parking.feature.main

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import com.urbanist.parking.R
import com.urbanist.parking.feature.rules.RulesActivity
import kotlinx.android.synthetic.main.activity_main.*
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionRequest
import com.urbanist.parking.core.presentation.BaseActivity
import com.urbanist.parking.feature.onboarding.OnBoardingActivity
import com.urbanist.parking.feature.report.ReportActivity


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rulesButton.setOnClickListener {
            startActivity(Intent(this, RulesActivity::class.java))
        }
        reportButton.setOnClickListener {
            requestGeoAndCameraPermissions()
        }
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val isFirst = prefs.getBoolean("first", true)
        if (isFirst) {
            startActivity(Intent(this, OnBoardingActivity::class.java))
            prefs.edit().putBoolean("first", false).apply()
        }
    }

    private fun requestGeoAndCameraPermissions() {
        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    startActivity(Intent(this@MainActivity, ReportActivity::class.java))
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {

                }
            }).check()
    }
}
