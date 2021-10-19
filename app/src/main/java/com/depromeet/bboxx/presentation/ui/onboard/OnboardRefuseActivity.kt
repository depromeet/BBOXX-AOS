package com.depromeet.bboxx.presentation.ui.onboard

import android.os.Bundle
import com.depromeet.bboxx.R
import com.depromeet.bboxx.presentation.base.BaseActivity
import com.depromeet.bboxx.presentation.ui.navigation.NavigatorUI
import kotlinx.android.synthetic.main.activity_nickname.btn_board_yes
import kotlinx.android.synthetic.main.activity_onboarding_refuse.*

class OnboardRefuseActivity: BaseActivity(R.layout.activity_onboarding_refuse) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btn_board_yes.setOnClickListener {
            NavigatorUI.toLogin(this)
        }

        txt_board_hate.setOnClickListener {
            NavigatorUI.toReRefuse(this)
        }
    }
}