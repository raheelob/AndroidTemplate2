package com.example.c24raheel.misc

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapters {
    @BindingAdapter("imagePath")
    @JvmStatic
    fun loadImageUrl(imageView: AppCompatImageView, url: String?) {
        url?.let {
            Glide
                .with(imageView.context)
                .load(url)
                .into(imageView)
        }
    }
}