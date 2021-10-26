package com.depromeet.bboxx.presentation.extension

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

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