package com.app.check.domain.entity

import com.google.gson.annotations.SerializedName

data class Header(

	@field:SerializedName("headerDescription")
	val headerDescription: String? = null,

	@field:SerializedName("headerTitle")
	val headerTitle: String? = null
)