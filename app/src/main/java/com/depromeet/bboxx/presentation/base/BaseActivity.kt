package com.depromeet.bboxx.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.depromeet.bboxx.presentation.ui.rxbus.RxBus
import com.depromeet.bboxx.util.ActivityManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity(@LayoutRes private val contentLayoutId: Int = 0) : AppCompatActivity(),
    CoroutineScope {

    val disposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    private lateinit var coroutineJob: Job
    private lateinit var sessionJob: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + coroutineJob

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (contentLayoutId > 0) {
            setContentView(contentLayoutId)
        }
        coroutineJob = SupervisorJob()
        ActivityManager.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.removeActivity(this)
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
}