package com.urbanist.parking.feature.report

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.urbanist.parking.R
import kotlinx.android.synthetic.main.activity_report.*
import android.provider.MediaStore
import android.content.Intent
import android.graphics.Bitmap
import android.location.Location
import android.location.LocationManager
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.urbanist.parking.core.dagger.AppComponent
import javax.inject.Inject
import java.io.ByteArrayOutputStream
import com.urbanist.parking.feature.report.MyLocation.LocationResult


class ReportActivity : AppCompatActivity() {

    @Inject
    lateinit var fireStore: FirebaseFirestore
    @Inject
    lateinit var fireStorage: FirebaseStorage

    private lateinit var thumbnail: Bitmap
    private lateinit var location: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppComponent.instance.inject(this)
        setContentView(R.layout.activity_report)
        val locationResult = object : LocationResult() {
            override fun gotLocation(location: Location) {
                this@ReportActivity.location = location
            }
        }
        val myLocation = MyLocation()
        myLocation.getLocation(this, locationResult)
        imageView.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST)
        }
        sendButton.setOnClickListener {
            sendReport()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        when (requestCode) {
            CAMERA_PIC_REQUEST -> if (resultCode == Activity.RESULT_OK) {
                thumbnail = data?.extras?.get("data") as Bitmap
                imageView.setImageBitmap(thumbnail)
                sendButton.isEnabled = true
            }
        }
    }

    private fun sendReport() {
        sendButton.isEnabled = false
        val outputStream = ByteArrayOutputStream()
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        val data = outputStream.toByteArray()
        val path = fireStorage.reference.child("$thumbnail")
        val url = path.toString()
        path.putBytes(data)
            .addOnFailureListener {
                Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
                sendButton.isEnabled = true
            }
            .addOnSuccessListener {
                val report = hashMapOf<String, String>()
                report["image_url"] = url
                report["location"] = "${location.latitude}:${location.longitude}"
                    fireStore.collection("reports")
                        .add(report)
                        .addOnSuccessListener { documentReference ->
                            Toast.makeText(this, "Репорт успешно отпрвлен!", Toast.LENGTH_SHORT).show()
                            imageView.setImageResource(0)
                            Log.d("kek", "DocumentSnapshot added with ID: ${documentReference.id}")
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
                            sendButton.isEnabled = true
                            Log.w("kek", "Error adding document", e)
                        }

            }
    }

    companion object {
        const val CAMERA_PIC_REQUEST = 20000
    }
}