package com.urbanist.parking.feature.report

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import com.urbanist.parking.R
import com.urbanist.parking.core.presentation.BaseActivity
import com.urbanist.parking.databinding.ActivityReportBinding
import javax.inject.Inject
import android.view.MenuItem
import com.urbanist.parking.feature.recomendation.RecommendationActivity

class ReportActivity : BaseActivity<ActivityReportBinding>() {

    @Inject
    lateinit var viewModel: ReportViewModel

    override val layoutId: Int = com.urbanist.parking.R.layout.activity_report

    override fun initBinding() {
        requireBinding().viewModel = viewModel
    }

    override fun initViewModel(state: Bundle?) {
        viewModel.onBind()
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
/*

    private var thumbnail: Bitmap? = null
    private var thumbnailSecond: Bitmap? = null
    private var thumbnailThird: Bitmap? = null
    private var currentPhoto = 0
    private lateinit var location: Location

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.report_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        startActivity(Intent(this, RecommendationActivity::class.java))
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppComponent.instance.inject(this)
        setContentView(R.layout.activity_report)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageViewSecond.scaleType = ImageView.ScaleType.CENTER_CROP
        imageViewThird.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setOnClickListener {
            openCamera(0)
        }
        imageViewSecond.setOnClickListener {
            openCamera(ic_icon)
        }
        imageViewThird.setOnClickListener {
            openCamera(ic_report_photo_1)
        }
        sendButton.setOnClickListener {
            sendReport()
        }
    }

    private fun openCamera(numPhoto: Int) {
        currentPhoto = numPhoto
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CAMERA_PIC_REQUEST -> if (resultCode == Activity.RESULT_OK) {
                val bitmap = data?.extras?.get("data") as Bitmap
                when (currentPhoto) {
                    0 -> thumbnail = bitmap
                    ic_icon -> thumbnailSecond = bitmap
                    ic_report_photo_1 -> thumbnailThird = bitmap
                }
                imageView.setImageBitmap(thumbnail)
                imageViewSecond.setImageBitmap(thumbnailSecond)
                imageViewThird.setImageBitmap(thumbnailThird)
                sendButton.isEnabled = thumbnail != null && thumbnailSecond != null && thumbnailThird != null
            }
        }
    }

    private fun sendReport() {
        val locationResult = object : LocationResult() {
            override fun gotLocation(location: Location) {
                this@ReportActivity.location = location
            }
        }
        val myLocation = MyLocation()
        myLocation.getLocation(this, locationResult)
        sendPhoto(thumbnail!!)
        sendPhoto(thumbnailSecond!!)
        sendPhoto(thumbnailThird!!)
        val url1 =  "$thumbnail"
        val url2 =  "$thumbnailSecond"
        val url3 =  "$thumbnailThird"
        val report = hashMapOf<String, String>()
        report["image_name_1"] = url1
        report["image_name_2"] = url2
        report["image_name_3"] = url3
        report["comment"] = comment.text.toString()
        if (::location.isInitialized) {
            report["location"] = "${location.latitude}:${location.longitude}"
        } else {
            Toast.makeText(this, "Включите определение местоположения", Toast.LENGTH_SHORT).show()
            return
        }
        progressBar.visibility = View.VISIBLE
        sendButton.isEnabled = false
        fireStore.collection("reports")
            .add(report)
            .addOnSuccessListener { documentReference ->
                progressBar.visibility = View.GONE
                Toast.makeText(this, "Репорт успешно отпрвлен!", Toast.LENGTH_SHORT).show()
                imageView.setImageResource(0)
                imageViewSecond.setImageResource(0)
                imageViewThird.setImageResource(0)
                thumbnail = null
                thumbnailSecond = null
                thumbnailThird = null
                comment.setText("")
                Log.d("kek", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
                sendButton.isEnabled = true
                Log.w("kek", "Error adding document", e)
            }
    }

    private fun sendPhoto(bitmap: Bitmap) {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        val data = outputStream.toByteArray()
        val path = fireStorage.reference.child("$bitmap")
        path.putBytes(data)
    }


    companion object {
        const val CAMERA_PIC_REQUEST = 20000
    }*/
}