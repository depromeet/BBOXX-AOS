package com.depromeet.bboxx.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.depromeet.bboxx.presentation.ui.rxbus.RxBus
import com.depromeet.bboxx.util.ActivityManager
import com.depromeet.bboxx.util.VersionUtils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import kotlinx.coroutines.*

abstract class BaseActivity<T : ViewDataBinding> (@LayoutRes private val contentLayoutId: Int) : AppCompatActivity(){

    val disposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    protected lateinit var binding: T


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, contentLayoutId)

        ActivityManager.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.removeActivity(this)
        disposable.clear()
    }

    fun setStatusBarColor(@ColorRes statusColor:Int){
        if(VersionUtils.isOverAPI23()){
            window?.run {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                statusBarColor = ContextCompat.getColor(this@BaseActivity,statusColor)
            }
        }else if(VersionUtils.isOverAPI21()){
            window?.run {
                statusBarColor = ContextCompat.getColor(this@BaseActivity,statusColor)
            }
        }
    }

    protected fun <T> subscribeEvent(cls: Class<T>, func: (t: T) -> Unit) {
        disposable += RxBus.toObservable(cls)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                func.invoke(it)},
                { e -> e.printStackTrace() })
    }
}
