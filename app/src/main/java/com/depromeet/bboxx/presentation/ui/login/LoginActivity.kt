package com.depromeet.bboxx.presentation.ui.login

import android.os.Bundle
import androidx.activity.viewModels
import com.depromeet.bboxx.R
import com.depromeet.bboxx.constants.Constants.C_SUCCESS
import com.depromeet.bboxx.domain.enums.PlatformType
import com.depromeet.bboxx.domain.enums.SnsVerifyEvent
import com.depromeet.bboxx.presentation.base.BaseActivity
import com.depromeet.bboxx.presentation.dialog.SystemErrorDialog
import com.depromeet.bboxx.presentation.event.SnsErrorEvent
import com.depromeet.bboxx.presentation.extension.observeNonNull
import com.depromeet.bboxx.presentation.ui.navigation.NavigatorUI.toGoogleLogin
import com.depromeet.bboxx.presentation.ui.navigation.NavigatorUI.toKakaoLogin
import com.depromeet.bboxx.presentation.ui.navigation.NavigatorUI.toNickName
import com.depromeet.bboxx.presentation.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: BaseActivity(R.layout.activity_login) {

    private val loginViewModel: LoginViewModel by viewModels()

    private var snsPlatformType = PlatformType.UNKNOWN
    private var userEmail = ""

    private var systemErrorDialog: SystemErrorDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btn_google_login.setOnClickListener {
            toGoogleLogin(this)
        }

        btn_kakao_login.setOnClickListener {
            toKakaoLogin(this)
        }

        loginViewModel.snsLoginResult.observeNonNull(this){ result ->
            if(result == C_SUCCESS){
                toNickName(this)
            }

        }

        subscribeEvent(SnsVerifyEvent::class.java, ::userSnsVerifyEvent)
        subscribeEvent(SnsErrorEvent::class.java, ::snsSignInErrorEvent)
    }

    private fun userSnsVerifyEvent(event: SnsVerifyEvent) {
        snsPlatformType = event.snsPlatformType
        userEmail = event.email

    }

    private fun snsSignInErrorEvent(event: SnsErrorEvent) {
        systemErrorDialog =
            SystemErrorDialog(this, confirmClick = {
                systemErrorDialog?.dismiss()
            })
        systemErrorDialog?.setCancelable(false)
        systemErrorDialog?.show()
    }
}