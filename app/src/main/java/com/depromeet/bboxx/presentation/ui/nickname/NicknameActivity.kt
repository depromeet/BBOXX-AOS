package com.depromeet.bboxx.presentation.ui.nickname

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.ActivityNicknameBinding
import com.depromeet.bboxx.presentation.base.BaseActivity
import com.depromeet.bboxx.presentation.extension.observeNonNull
import com.depromeet.bboxx.presentation.viewmodel.NicknameViewModel
import com.depromeet.bboxx.util.SharedPreferenceUtil.initSharedPreference
import com.depromeet.bboxx.util.constants.SharedConstants.C_NICKNAME_SHRED
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NicknameActivity : BaseActivity<ActivityNicknameBinding>(R.layout.activity_nickname){

    private val nicknameViewModel : NicknameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

        nicknameViewModel.nickName.observeNonNull(this){
            Log.d("NICKNAME " ,it.toString())
            // Error(exception=java.net.UnknownServiceException: CLEARTEXT communication to ec2-3-35-36-228.ap-northeast-2.compute.amazonaws.com not permitted by network security policy)
        }

        nicknameViewModel.likeEvent.observeNonNull(this){
            Log.d("NICKNAME " ,"I Like")
        }
    }

    private fun init() {
        with(binding) {
            lifecycleOwner = this@NicknameActivity
            vm = nicknameViewModel
        }

        initSharedPreference(this, C_NICKNAME_SHRED)

        nicknameViewModel.initNickName()
    }
}