package com.depromeet.bboxx.presentation.ui.onboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.depromeet.bboxx.R

class OnboardActivity : AppCompatActivity() {

    private val onboardPagerModelListAdatper: OnboardPagerModelListAdatper by lazy {
        OnboardPagerModelListAdatper()
    }

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
    }
}