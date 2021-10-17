package com.depromeet.bboxx.presentation.extension

import androidx.lifecycle.*

operator fun Lifecycle.plusAssign(observer: LifecycleObserver) = this.addObserver(observer)


inline fun <T> LiveData<T>.observe(lifecycleOwner: LifecycleOwner, crossinline body: (T?) -> Unit) {
    observe(lifecycleOwner, Observer { bean: T? ->
        body(bean)
    })
}

inline fun <T> LiveData<T>.observeNonNull(lifecycleOwner: LifecycleOwner, crossinline body: (T) -> Unit) {
    observe(lifecycleOwner, Observer {bean: T? ->
        bean?.let {
            body(it)
        }
    })
}