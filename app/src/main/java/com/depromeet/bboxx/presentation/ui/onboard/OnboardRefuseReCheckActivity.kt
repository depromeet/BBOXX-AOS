package com.depromeet.bboxx.presentation.ui.onboard

import android.os.Bundle
import com.depromeet.bboxx.R
import com.depromeet.bboxx.presentation.base.BaseActivity
import com.depromeet.bboxx.presentation.ui.navigation.NavigatorUI
import kotlinx.android.synthetic.main.activity_onboarding_refuse_recheck.*

class OnboardRefuseReCheckActivity: BaseActivity(R.layout.activity_onboarding_refuse_recheck) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btn_board_ok.setOnClickListener {
            NavigatorUI.toLogin(this)
        }

        txt_board_re_hate.setOnClickListener {
            finish()
        }
    }
}