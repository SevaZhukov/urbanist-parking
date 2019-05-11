package com.urbanist.parking.utils

import java.util.*

fun Array<*>.hasNull(): Boolean {
	forEach {
		if (it == null) {
			return true
		}
	}
	return false
}