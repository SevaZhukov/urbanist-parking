package com.urbanist.parking.feature.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.memebattle.memes.utils.info.snack
import com.memebattle.memes.utils.permissions.isPermissionsGranted
import com.memebattle.memes.utils.permissions.requestPermissions
import com.urbanist.parking.R
import com.urbanist.parking.feature.report.presentation.ReportActivity
import com.urbanist.parking.feature.rules.RulesActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private companion object {
        const val PERMISSIONS_REQUEST_CODE = 483
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rulesButton.setOnClickListener {
            startActivity(Intent(this, RulesActivity::class.java))
        }
        reportButton.setOnClickListener {
            routeToReport()
        }
    }

    private fun routeToReport() {
        if (isPermissionsGranted(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CAMERA
            )
        ) {
            startActivity(Intent(this@MainActivity, ReportActivity::class.java))
        } else {
            requestPermissions(
                PERMISSIONS_REQUEST_CODE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CAMERA
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val permissionsResults = permissions.mapIndexed { index, permission ->
            permission to grantResults[index]
        }.toMap()

        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            checkPermissionResult(
                permissionsResults,
                R.string.permissions_explanation,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CAMERA
            ) {
                startActivity(Intent(this@MainActivity, ReportActivity::class.java))
            }
        }
    }

    private fun checkPermissionResult(
        permissionsResults: Map<String, Int>,
        @StringRes explanation: Int,
        vararg permissions: String,
        onSuccess: () -> Unit
    ) {
        permissions.forEach { permission ->
            if ((permissionsResults[permission] == PackageManager.PERMISSION_GRANTED).not()) {
                snack(getString(explanation))
                return
            }
        }
        onSuccess()
    }
}
