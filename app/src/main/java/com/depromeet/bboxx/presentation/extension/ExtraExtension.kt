package com.depromeet.bboxx.presentation.extension

import android.app.Activity
import androidx.fragment.app.Fragment

inline fun <reified T: Any> Activity.extra(key: String, default: T? = null) = lazy {
    val value = intent?.extras?.get(key)
    if (value is T) value else default
}

inline fun <reified T: Any> Activity.extraNotNull(key: String, default: T? = null) = lazy {
    val value = intent?.extras?.get(key)
    requireNotNull(if (value is T) value else default) { key }
}

inline fun <reified T: Any> Activity.extraSerializable(key: String, default: T? = null) = lazy {
    val value = intent?.extras?.getSerializable(key)
    if (value is T) value else default
}

inline fun <reified T: Any> Activity.extraSerializableNotNull(key: String, default: T? = null) = lazy {
    val value = intent?.extras?.getSerializable(key)
    requireNotNull(if (value is T) value else default) { key }
}

inline fun <reified T: Any> Fragment.extra(key: String, default: T? = null) = lazy {
    val value = arguments?.get(key)
    if (value is T) value else default
}

inline fun <reified T: Any> Fragment.extraNotNull(key: String, default: T? = null) = lazy {
    val value = arguments?.get(key)
    requireNotNull(if (value is T) value else default) { key }
}

inline fun <reified T: Any> Fragment.extraSerializable(key: String, default: T? = null) = lazy {
    val value = arguments?.getSerializable(key)
    if (value is T) value else default
}

inline fun <reified T: Any> Fragment.extraSerializableNotNull(key: String, default: T? = null) = lazy {
    val value = arguments?.getSerializable(key)
    requireNotNull(if (value is T) value else default) { key }
}
