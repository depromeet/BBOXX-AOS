package com.depromeet.bboxx.presentation.ui.onboard

import android.os.Bundle
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.ActivityOnboardingRefuseRecheckBinding
import com.depromeet.bboxx.presentation.base.BaseActivity
import com.depromeet.bboxx.presentation.ui.navigation.NavigatorUI

class OnboardRefuseReCheckActivity: BaseActivity<ActivityOnboardingRefuseRecheckBinding>(R.layout.activity_onboarding_refuse_recheck) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()

        binding.btnBoardOk.setOnClickListener {
            NavigatorUI.toLogin(this)
            finish()
        }

        binding.txtBoardReHate.setOnClickListener {
            finish()
        }
    }

    private fun initBinding() {
        with(binding) {
            lifecycleOwner = this@OnboardRefuseReCheckActivity
        }
    }
}