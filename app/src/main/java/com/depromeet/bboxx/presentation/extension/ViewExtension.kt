package com.depromeet.bboxx.presentation.extension

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun Context.inflateLayout(layoutResId: Int, parent: ViewGroup, attachToRoot: Boolean): View {
    return inflateView(this, layoutResId, parent, attachToRoot)
}
private fun inflateView(
    context: Context,
    layoutResId: Int,
    parent: ViewGroup?,
    attachToRoot: Boolean
): View {
    return LayoutInflater.from(context).inflate(layoutResId, parent, attachToRoot)
}

@BindingAdapter("ImageDrawable", "ImageDrawableError")
fun imageViewAdapter(view: ImageView, res: String?, error: Drawable) {
    val options = RequestOptions()
        .error(error)

    Glide.with(view.context)
        .load(res)
        .apply(options)
        .into(view)

}