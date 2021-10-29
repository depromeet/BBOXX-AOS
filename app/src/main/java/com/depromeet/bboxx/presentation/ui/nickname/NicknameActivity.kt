package com.depromeet.bboxx.presentation.ui.nickname

import android.os.Bundle
import androidx.activity.viewModels
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.ActivityNicknameBinding
import com.depromeet.bboxx.presentation.base.BaseActivity
import com.depromeet.bboxx.presentation.extension.extraNotNull
import com.depromeet.bboxx.presentation.extension.observeNonNull
import com.depromeet.bboxx.presentation.ui.navigation.NavigatorUI.toMain
import com.depromeet.bboxx.presentation.viewmodel.NicknameViewModel
import com.depromeet.bboxx.util.SharedPreferenceUtil.initSharedPreference
import com.depromeet.bboxx.util.SharedPreferenceUtil.setDataSharedPreference
import com.depromeet.bboxx.util.constants.SharedConstants.C_JWT_KEY
import com.depromeet.bboxx.util.constants.SharedConstants.C_JWT_SHRED
import com.depromeet.bboxx.util.constants.SharedConstants.C_NICKNAME_SHRED
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NicknameActivity : BaseActivity<ActivityNicknameBinding>(R.layout.activity_nickname){

    companion object {
        val EXTRA_ACCESS_TOKEN = "extra_access_token"
        val EXTRA_PROVIDER_TYPE = "extra_provider_type"
    }

    private val nicknameViewModel : NicknameViewModel by viewModels()
    private val accessToken by extraNotNull(EXTRA_ACCESS_TOKEN, "")
    private val providerType by extraNotNull(EXTRA_PROVIDER_TYPE, "")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

        nicknameViewModel.nickName.observeNonNull(this){
            //  Error Exception 처리 필요
        }

        nicknameViewModel.likeResult.observeNonNull(this){ token ->
            if(token.isNotBlank()){
                //setJwtValue(token)
                onMoveMain()
            }
        }
    }

    private fun init() {
        with(binding) {
            lifecycleOwner = this@NicknameActivity
            vm = nicknameViewModel
        }

        initSharedPreference(this, C_NICKNAME_SHRED)
        initSharedPreference(this, C_JWT_SHRED)

        nicknameViewModel.setAccessToken(accessToken)
        nicknameViewModel.setProviderType(providerType)
        nicknameViewModel.initNickName()
    }


    private fun setJwtValue(token: String){
        setDataSharedPreference(token, C_JWT_KEY)
    }

    private fun onMoveMain(){
        toMain(this)
    }
}