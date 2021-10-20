package com.depromeet.bboxx.presentation.ui.login

import android.os.Bundle
import android.util.Log
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.ActivitySnsLoginBinding
import com.depromeet.bboxx.domain.enums.PlatformType
import com.depromeet.bboxx.domain.enums.SnsVerifyEvent
import com.depromeet.bboxx.presentation.base.BaseActivity
import com.depromeet.bboxx.presentation.ui.rxbus.RxBus
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.User

class KakaoTalkLoginActivity: BaseActivity<ActivitySnsLoginBinding>(R.layout.activity_sns_login) {
    private var accessToken = ""
    private var socialUserId = ""
    private var mobileNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
            if (token != null) {
                accessToken = token.accessToken

                if (accessToken.isNotEmpty()) getKakaoUserInfo()
            }
            else if(error != null){
                RxBus.send(error)
                finish()
            }
        }
    }

    private fun getKaKaoTalkUserInfo() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                RxBus.send(error)
                finish()
                Log.e("TAG1", "사용자 정보 요청 실패", error)
            } else if (user != null) {
                var scopes = mutableListOf<String>()

                if (user.kakaoAccount?.emailNeedsAgreement == true) {
                    scopes.add("account_email")
                }
                if (user.kakaoAccount?.birthdayNeedsAgreement == true) {
                    scopes.add("birthday")
                }
                if (user.kakaoAccount?.birthyearNeedsAgreement == true) {
                    scopes.add("birthyear")
                }
                if (user.kakaoAccount?.genderNeedsAgreement == true) {
                    scopes.add("gender")
                }
                if (user.kakaoAccount?.phoneNumberNeedsAgreement == true) {
                    scopes.add("phone_number")
                }
                if (user.kakaoAccount?.profileNeedsAgreement == true) {
                    scopes.add("profile")
                }
                if (user.kakaoAccount?.ageRangeNeedsAgreement == true) {
                    scopes.add("age_range")
                }
                if (user.kakaoAccount?.ciNeedsAgreement == true) {
                    scopes.add("account_ci")
                }


                if (scopes.count() > 0) {

                    UserApiClient.instance.loginWithNewScopes(this, scopes) { token, error ->
                        if (error != null) {
                            RxBus.send(error)
                            finish()
                        } else {
                            // 사용자 정보 재요청자
                            UserApiClient.instance.me { user, error ->
                                if (error != null) {
                                    RxBus.send(error)
                                    finish()
                                } else if (user != null) {
                                    Log.i("TAG", "사용자 정보 요청 성공")

                                    getUserInfo(user)
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    private fun getKakaoUserInfo() {
        UserApiClient.instance.me { user, error ->
            if (user != null) {
                var email = user.kakaoAccount?.email

                //user의 개인정보가 null이면 개인정보 수집 동의 로직 실
                if (email.isNullOrBlank()) {
                    getKaKaoTalkUserInfo()
                }

                getUserInfo(user)
            }
        }
    }

    private fun getUserInfo(user: User?) {
        mobileNumber = user?.kakaoAccount?.phoneNumber ?: ""
        socialUserId = user?.id.toString()

        RxBus.send(
            SnsVerifyEvent(user?.kakaoAccount?.email ?: "", user?.kakaoAccount?.phoneNumber ?: "",
            user?.id.toString(), PlatformType.KAKAO, accessToken= accessToken)
        )

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}