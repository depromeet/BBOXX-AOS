package com.depromeet.bboxx.presentation.ui.rxbus

import com.jakewharton.rxrelay3.PublishRelay
import io.reactivex.rxjava3.core.Observable


object RxBus {

    private val bus = PublishRelay.create<Any>().toSerialized()

    fun send(event: Any) {
        bus.accept(event)
    }

    fun toObservable(): Observable<Any> {
        return bus
    }

    fun <T> toObservable(cls: Class<T>): Observable<T> {
        return bus.ofType(cls)
    }
}