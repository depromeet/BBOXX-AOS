package com.depromeet.bboxx.presentation.ui.login

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.ActivityLoginBinding
import com.depromeet.bboxx.domain.enums.ProviderType
import com.depromeet.bboxx.domain.enums.SnsVerifyEvent
import com.depromeet.bboxx.presentation.base.BaseActivity
import com.depromeet.bboxx.presentation.dialog.SystemErrorDialog
import com.depromeet.bboxx.presentation.event.SnsErrorEvent
import com.depromeet.bboxx.presentation.extension.observeNonNull
import com.depromeet.bboxx.presentation.ui.navigation.NavigatorUI.toGoogleLogin
import com.depromeet.bboxx.presentation.ui.navigation.NavigatorUI.toKakaoLogin
import com.depromeet.bboxx.presentation.ui.navigation.NavigatorUI.toMain
import com.depromeet.bboxx.presentation.ui.navigation.NavigatorUI.toNickName
import com.depromeet.bboxx.presentation.viewmodel.*
import com.depromeet.bboxx.util.SharedPreferenceUtil.initSharedPreference
import com.depromeet.bboxx.util.SharedPreferenceUtil.setDataStringSharedPreference
import com.depromeet.bboxx.util.constants.SharedConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val loginViewModel: LoginViewModel by viewModels()

    private var snsPlatformType = ProviderType.UNKNOWN
    private var userToken = ""
    private var firebasetoken = ""
    private var systemErrorDialog: SystemErrorDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

        loginViewModel.snsLoginEvent.observeNonNull(this){ paltform ->
            //  SNS Click Event
            when(paltform){
                "kakao" -> {
                    snsPlatformType = ProviderType.KAKAO
                    toKakaoLogin(this)
                }
                "google" -> {
                    snsPlatformType = ProviderType.GOOGLE
                    toGoogleLogin(this)
                }
            }
            // Test code
            //moveActivityTest()
        }

        loginViewModel.token.observeNonNull(this){ token->
            if(token.isNotBlank()){
                initSharedPreference(this, SharedConstants.C_JWT_SHRED)
                setDataStringSharedPreference(token, SharedConstants.C_JWT_KEY)
                toMain(this)
                finish()
            }
            else{
                toNickName(this, accessToken = userToken, snsPlatformType.name)
                finish()
            }

        }

        subscribeEvent(SnsVerifyEvent::class.java, ::userSnsVerifyEvent)
        subscribeEvent(SnsErrorEvent::class.java, ::snsSignInErrorEvent)
    }

    private fun init() {
        with(binding) {
            lifecycleOwner = this@LoginActivity
            vm = loginViewModel
        }
    }

    private fun userSnsVerifyEvent(event: SnsVerifyEvent) {
        userToken = event.accessToken

        loginViewModel.signIn(event.accessToken, event.snsPlatformType.type)
    }

    private fun snsSignInErrorEvent(event: SnsErrorEvent) {
        Toast.makeText(this, "SNS Sign Error", Toast.LENGTH_SHORT).show()
//        systemErrorDialog =
//            SystemErrorDialog(this, confirmClick = {
//                systemErrorDialog?.dismiss()
//            })
//        systemErrorDialog?.setCancelable(false)
//        systemErrorDialog?.show()
    }

    private fun moveActivityTest(){
        toNickName(this, accessToken = "userToken", snsPlatformType.name)
        finish()
    }
}