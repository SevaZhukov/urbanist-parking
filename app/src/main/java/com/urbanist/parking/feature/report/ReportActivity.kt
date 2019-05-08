package com.urbanist.parking.feature.report

import android.app.Activity
import android.os.Bundle
import com.urbanist.parking.R
import kotlinx.android.synthetic.main.activity_report.*
import android.provider.MediaStore
import android.content.Intent
import android.graphics.Bitmap
import android.location.Location
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import javax.inject.Inject
import java.io.ByteArrayOutputStream
import com.urbanist.parking.feature.report.MyLocation.LocationResult
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.urbanist.parking.core.presentation.BaseActivity
import com.urbanist.parking.databinding.ActivityReportBinding
import com.urbanist.parking.feature.recomendation.RecommendationActivity


class ReportActivity : BaseActivity<ActivityReportBinding>() {
    override val layoutId: Int = R.layout.activity_report


////    @Inject
////    lateinit var fireStore: FirebaseFirestore
////    @Inject
////    lateinit var fireStorage: FirebaseStorage
//
//    private var thumbnail: Bitmap? = null
//    private var thumbnailSecond: Bitmap? = null
//    private var thumbnailThird: Bitmap? = null
//    private var currentPhoto = 0
//    private lateinit var location: Location
//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.report_menu, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        startActivity(Intent(this, RecommendationActivity::class.java))
//        return super.onOptionsItemSelected(item)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        AppComponent.instance.inject(this)
//        setContentView(R.layout.activity_report)
//        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
//        imageViewSecond.scaleType = ImageView.ScaleType.CENTER_CROP
//        imageViewThird.scaleType = ImageView.ScaleType.CENTER_CROP
//        imageView.setOnClickListener {
//            openCamera(0)
//        }
//        imageViewSecond.setOnClickListener {
//            openCamera(1)
//        }
//        imageViewThird.setOnClickListener {
//            openCamera(2)
//        }
//        sendButton.setOnClickListener {
//            sendReport()
//        }
//    }
//
//    private fun openCamera(numPhoto: Int) {
//        currentPhoto = numPhoto
//        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST)
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        when (requestCode) {
//            CAMERA_PIC_REQUEST -> if (resultCode == Activity.RESULT_OK) {
//                val bitmap = data?.extras?.get("data") as Bitmap
//                when (currentPhoto) {
//                    0 -> thumbnail = bitmap
//                    1 -> thumbnailSecond = bitmap
//                    2 -> thumbnailThird = bitmap
//                }
//                imageView.setImageBitmap(thumbnail)
//                imageViewSecond.setImageBitmap(thumbnailSecond)
//                imageViewThird.setImageBitmap(thumbnailThird)
//                sendButton.isEnabled = thumbnail != null && thumbnailSecond != null && thumbnailThird != null
//            }
//        }
//    }
//
//    private fun sendReport() {
//        val locationResult = object : LocationResult() {
//            override fun gotLocation(location: Location) {
//                this@ReportActivity.location = location
//            }
//        }
//        val myLocation = MyLocation()
//        myLocation.getLocation(this, locationResult)
//        sendPhoto(thumbnail!!)
//        sendPhoto(thumbnailSecond!!)
//        sendPhoto(thumbnailThird!!)
//        val url1 =  "$thumbnail"
//        val url2 =  "$thumbnailSecond"
//        val url3 =  "$thumbnailThird"
//        val report = hashMapOf<String, String>()
//        report["image_name_1"] = url1
//        report["image_name_2"] = url2
//        report["image_name_3"] = url3
//        report["comment"] = comment.text.toString()
//        if (::location.isInitialized) {
//            report["location"] = "${location.latitude}:${location.longitude}"
//        } else {
//            Toast.makeText(this, "Включите определение местоположения", Toast.LENGTH_SHORT).show()
//            return
//        }
//        progressBar.visibility = View.VISIBLE
//        sendButton.isEnabled = false
//        fireStore.collection("reports")
//            .add(report)
//            .addOnSuccessListener { documentReference ->
//                progressBar.visibility = View.GONE
//                Toast.makeText(this, "Репорт успешно отпрвлен!", Toast.LENGTH_SHORT).show()
//                imageView.setImageResource(0)
//                imageViewSecond.setImageResource(0)
//                imageViewThird.setImageResource(0)
//                thumbnail = null
//                thumbnailSecond = null
//                thumbnailThird = null
//                comment.setText("")
//                Log.d("kek", "DocumentSnapshot added with ID: ${documentReference.id}")
//            }
//            .addOnFailureListener { e ->
//                Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
//                sendButton.isEnabled = true
//                Log.w("kek", "Error adding document", e)
//            }
//    }
//
//    private fun sendPhoto(bitmap: Bitmap) {
//        val outputStream = ByteArrayOutputStream()
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
//        val data = outputStream.toByteArray()
//        val path = fireStorage.reference.child("$bitmap")
//        path.putBytes(data)
//    }
//
//
//    companion object {
//        const val CAMERA_PIC_REQUEST = 20000
//    }
}