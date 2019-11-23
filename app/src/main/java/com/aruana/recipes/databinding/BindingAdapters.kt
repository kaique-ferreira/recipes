package com.aruana.recipes.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    if (imageUrl == null) {
        return
    }

    Glide.with(view)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
}
