package com.lamnt.furniture.ui.binding

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImageUrl")
fun loadUrlImage(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView.context).load(it).into(imageView);
    }
}

@BindingAdapter("loadImageResource")
fun loadImageResource(imageView: ImageView,@DrawableRes res: Int) {
    (res != 0).let {
        Glide.with(imageView.context).load(res).into(imageView);
    }
}