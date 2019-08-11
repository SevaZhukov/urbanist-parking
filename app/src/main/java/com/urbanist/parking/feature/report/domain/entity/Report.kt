package com.urbanist.parking.feature.report.domain.entity

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class Report(
	@SerializedName("lat")
	val lat: Double,
	@SerializedName("lng")
	val lng: Double,
	@SerializedName("comment")
	val comment: String,
	@SerializedName("images")
	val images: ArrayList<String>?
)