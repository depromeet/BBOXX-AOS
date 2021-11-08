package com.depromeet.bboxx.presentation.extension

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.depromeet.bboxx.presentation.model.SelectCalendarModel
import com.depromeet.bboxx.presentation.utils.GlideApp

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

@BindingAdapter("ImageDrawable")
fun imageViewAdapter(view: ImageView, res: String?) {
    if(res.isNotBlank()){
        GlideApp.with(view.context)
            .load(res)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view)
    }
}

fun ImageView.loadUrl(url: String){
    GlideApp.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(this)
}

@BindingAdapter("CalendarBackTint")
fun calendarTint(textView: TextView, model: SelectCalendarModel) {
    textView.backgroundTintList = ColorStateList.valueOf(textView.resources.getColor(model.color))
}

@BindingAdapter("CalendarTextColor")
fun calendarTextColor(textView: TextView, model: SelectCalendarModel) {
    textView.setTextColor(ColorStateList.valueOf(textView.resources.getColor(model.textColor)))
}