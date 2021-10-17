package com.depromeet.bboxx.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.depromeet.bboxx.presentation.ui.rxbus.RxBus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment(@LayoutRes private val contentLayoutId: Int = 0): Fragment(), CoroutineScope{
    val CURRENT_FRAGMENT = "current-fragment"

    val disposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    private lateinit var coroutineJob: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + coroutineJob

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coroutineJob = SupervisorJob()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (contentLayoutId > 0) {
            return inflater.inflate(contentLayoutId, container, false)
        }
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
        coroutineJob.cancelChildren()
    }

    protected fun <T> subscribeEvent(cls: Class<T>, func: (t: T) -> Unit) {
        disposable += RxBus.toObservable(cls)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                func.invoke(it)},
                { e -> e.printStackTrace() })
    }

    fun requireNotNulls(vararg any: Any?) {
        any.forEach {
            requireNotNull(value = it)
        }
    }
}