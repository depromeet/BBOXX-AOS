package com.depromeet.bboxx.presentation.ui.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.depromeet.bboxx.R
import com.depromeet.bboxx.presentation.event.MoveToEvent
import com.depromeet.bboxx.presentation.extension.observeNonNull
import com.depromeet.bboxx.presentation.ui.navigation.NavigatorUI
import com.depromeet.bboxx.presentation.viewmodel.SplashViewModel

class SplashActivity: AppCompatActivity() {

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashViewModel.init()

        splashViewModel.naviToActivity.observeNonNull(this){ event ->
            when(event){
                MoveToEvent.ONBOARD -> NavigatorUI.toOnboard(this)
            }
        }
    }
}