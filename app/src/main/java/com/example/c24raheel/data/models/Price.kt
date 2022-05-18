package com.app.check.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Price(

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("value")
	val value: Double? = null
) : Parcelable