package com.example.c24raheel.data

import com.app.check.domain.entity.Header
import com.app.check.domain.entity.ProductsItem
import com.google.gson.annotations.SerializedName

data class HomePageResponse(

	@field:SerializedName("header")
	val header: Header? = null,

	@field:SerializedName("filters")
	val filters: List<String?>? = null,

	@field:SerializedName("products")
	val products: List<ProductsItem>? = null
)