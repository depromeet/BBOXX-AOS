package com.depromeet.bboxx.presentation.ui.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.ActivitySplashBinding
import com.depromeet.bboxx.presentation.base.BaseActivity
import com.depromeet.bboxx.presentation.event.MoveToEvent
import com.depromeet.bboxx.presentation.extension.observeNonNull
import com.depromeet.bboxx.presentation.ui.navigation.NavigatorUI
import com.depromeet.bboxx.presentation.viewmodel.SplashViewModel

class SplashActivity: BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStatusBarColor(R.color.black)
        splashViewModel.init()

        splashViewModel.naviToActivity.observeNonNull(this){ event ->
            when(event){
                MoveToEvent.ONBOARD -> NavigatorUI.toOnboard(this)
                MoveToEvent.OPEN -> {
                    changeImage()
                    splashViewModel.nextStep()
                }
            }
        }
//        //            @SuppressLint("SetTextI18n")
//            override fun onTick(p0: Long) {
//
//                when {
//                    p0 / 1000 >= 2 -> {
//                        val sdk = android.os.Build.VERSION.SDK_INT
//                        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
//                            binding.imgSplash.setBackgroundDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.img_splash_one) );
//                        } else {
//                            binding.imgSplash.background = ContextCompat.getDrawable(applicationContext, R.drawable.img_splash_one);
//                        }
//                    }
//
//                    p0 / 1000 >= 1 -> {
//                        val sdk = android.os.Build.VERSION.SDK_INT
//                        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
//                            binding.imgSplash.setBackgroundDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.img_splash_one) );
//                        } else {
//                            binding.imgSplash.background = ContextCompat.getDrawable(applicationContext, R.drawable.img_splash_one);
//                        }
//                    }
//                    else -> {
//                        val sdk = android.os.Build.VERSION.SDK_INT
//                        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
//                            binding.imgSplash.setBackgroundDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.img_splash_one) );
//                        } else {
//                            binding.imgSplash.background = ContextCompat.getDrawable(applicationContext, R.drawable.img_splash_one);
//                        }                    }
//                }
//            }
//
//            override fun onFinish() {
//                NavigatorUI.toOnboard(applicationContext)
//            }
//        }.start()
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

    override fun onStop() {
        super.onStop()

        finish()
    }
}