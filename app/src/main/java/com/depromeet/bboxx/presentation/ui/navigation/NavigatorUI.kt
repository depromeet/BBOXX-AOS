package com.depromeet.bboxx.presentation.ui.navigation

import android.content.Context
import com.depromeet.bboxx.presentation.ui.login.GoogleLoginActivity
import com.depromeet.bboxx.presentation.ui.login.KakaoTalkLoginActivity
import com.depromeet.bboxx.presentation.ui.login.LoginActivity
import com.depromeet.bboxx.presentation.ui.nickname.NicknameActivity
import com.depromeet.bboxx.presentation.ui.onboard.OnboardActivity
import com.depromeet.bboxx.presentation.ui.onboard.OnboardRefuseActivity
import com.depromeet.bboxx.presentation.ui.onboard.OnboardRefuseReCheckActivity
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.noAnimation
import org.jetbrains.anko.singleTop

object NavigatorUI {

    fun toOnboard(context: Context){
        with(context){
            startActivity(intentFor<OnboardActivity>()
                .clearTop()
                .singleTop()
                .noAnimation())
        }
    }

    fun toRefuse(context: Context){
        with(context){
            startActivity(intentFor<OnboardRefuseActivity>()
                .clearTop()
                .singleTop()
                .noAnimation())
        }
    }

    fun toReRefuse(context: Context){
        with(context){
            startActivity(intentFor<OnboardRefuseReCheckActivity>()
                .clearTop()
                .singleTop()
                .noAnimation())
        }
    }

    fun toLogin(context: Context){
        with(context){
            startActivity(intentFor<LoginActivity>()
                .clearTop()
                .singleTop()
                .noAnimation())
        }
    }

    fun toGoogleLogin(context: Context){
        with(context) {
            startActivity(intentFor<GoogleLoginActivity>().noAnimation())
        }
    }

    fun toKakaoLogin(context: Context){
        with(context) {
            startActivity(intentFor<KakaoTalkLoginActivity>().noAnimation())
        }
    }

    fun toNickName(context: Context, accessToken: String){
        with(context){
            startActivity(intentFor<NicknameActivity>(NicknameActivity.EXTRA_ACCESS_TOKEN to accessToken)
                .clearTop()
                .singleTop()
                .noAnimation())
        }
    }
}