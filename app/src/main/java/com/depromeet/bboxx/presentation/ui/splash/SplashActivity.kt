package com.depromeet.bboxx.presentation.ui.splash

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.ActivitySplashBinding
import com.depromeet.bboxx.presentation.base.BaseActivity
import com.depromeet.bboxx.presentation.event.MoveToEvent
import com.depromeet.bboxx.presentation.extension.observeNonNull
import com.depromeet.bboxx.presentation.ui.navigation.NavigatorUI
import com.depromeet.bboxx.presentation.viewmodel.SplashViewModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.Observables
import io.reactivex.rxjava3.kotlin.toObservable


class SplashActivity: BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private val splashViewModel: SplashViewModel by viewModels()

    //요청하고 싶은 권한들을 String 타입의 배열로 선언
    private val permissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.RECORD_AUDIO
    )

    //요청 코드 선언
    private val REQUEST_PERMISSION_CODE = 0
    private val disposalBag = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStatusBarColor(R.color.black)
        splashViewModel.init()

        splashViewModel.naviToActivity.observeNonNull(this){ event ->
            when(event){
                MoveToEvent.ONBOARD -> NavigatorUI.toOnboard(this)
                MoveToEvent.OPEN -> {
                    reqPermissions(this, getPermissionList(permissions))
                }
            }
        }

    }

    private fun changeImage(){
        runOnUiThread {
            val sdk = android.os.Build.VERSION.SDK_INT
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                binding.imgSplash.setBackgroundDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.img_splash_one) );
            } else {
                binding.imgSplash.background = ContextCompat.getDrawable(applicationContext, R.drawable.img_splash_one);
            }
        }
    }


    //권한 목록을 Single<List<String>>로 변환
    private fun getPermissionList(permissions:Array<String>): Single<List<String>> {
        return permissions.toObservable()
            .filter { permission ->
                PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, permission)
            }.toList()
    }

    private fun reqPermissions(activity: Activity, list: Single<List<String>>) {
        val disposal = list.subscribe { list ->
            if (list.isNotEmpty()) {
                ActivityCompat.requestPermissions(activity, list.toTypedArray(), REQUEST_PERMISSION_CODE)
            } else {
                //이미 이전에 권한을 획득했음.
                changeImage()
                splashViewModel.nextStep()
            }
        }
        disposalBag.add(disposal)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_CODE) {
            val disposal = Observables.zip(permissions.toObservable(), grantResults.toObservable())
                .all {
                    it.second == PackageManager.PERMISSION_GRANTED
                }.subscribe { t1: Boolean, t2: Throwable? ->
                    if (t2 != null || !t1) {
                        //권한 획득 못함
                        Toast.makeText(this,"권한 요청이 필요합니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        //권한 획득
                        changeImage()
                        splashViewModel.nextStep()
                    }
                }
            disposalBag.add(disposal)
        }
    }

    override fun onStop() {
        super.onStop()

        finish()
    }
}