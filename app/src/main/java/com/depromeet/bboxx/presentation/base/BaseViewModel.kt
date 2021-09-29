package com.example.navermoviesearch.presentation.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {
    val disposable: CompositeDisposable = CompositeDisposable()
    private val coroutineJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + coroutineJob


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
        coroutineJob.cancel()
    }
}