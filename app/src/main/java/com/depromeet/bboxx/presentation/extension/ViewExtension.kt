package com.depromeet.bboxx.presentation.extension

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.depromeet.bboxx.presentation.model.SelectCalendarModel

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
    Glide.with(view.context)
        .load(res)
        .into(view)

}

@BindingAdapter("CalendarBackTint")
fun calendarTint(textView: TextView, model: SelectCalendarModel){
    textView.backgroundTintList = ColorStateList.valueOf(textView.resources.getColor( model.color))
}

@BindingAdapter("CalendarTextColor")
fun calendarTextColor(textView: TextView, model: SelectCalendarModel){
    textView.setTextColor(ColorStateList.valueOf(textView.resources.getColor(model.textColor)))
}