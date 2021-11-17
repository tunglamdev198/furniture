package com.lamnt.furniture.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImageUrl")
fun loadUrlImage(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView.context).load(it).into(imageView);
    }
}