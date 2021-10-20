package com.depromeet.bboxx.presentation.ui.onboard

import android.os.Bundle
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.ActivityOnboardingRefuseBinding
import com.depromeet.bboxx.presentation.base.BaseActivity
import com.depromeet.bboxx.presentation.ui.navigation.NavigatorUI

class OnboardRefuseActivity: BaseActivity<ActivityOnboardingRefuseBinding>(R.layout.activity_onboarding_refuse) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()

        binding.btnBoardYes.setOnClickListener {
            NavigatorUI.toLogin(this)
            finish()
        }

        binding.txtBoardHate.setOnClickListener {
            NavigatorUI.toReRefuse(this)
            finish()
        }
    }

    private fun initBinding() {
        with(binding) {
            lifecycleOwner = this@OnboardRefuseActivity
        }
    }
}