package com.depromeet.bboxx.presentation.ui.mypage

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.ActivityMypageBinding
import com.depromeet.bboxx.presentation.base.BaseActivity
import com.depromeet.bboxx.presentation.utils.CustomTopView
import com.depromeet.bboxx.presentation.viewmodel.MyPageViewModel
import com.depromeet.bboxx.util.SharedPreferenceUtil.getDataStringSharedPreference
import com.depromeet.bboxx.util.SharedPreferenceUtil.initSharedPreference
import com.depromeet.bboxx.util.VersionUtils.appVersionInfo
import com.depromeet.bboxx.util.constants.SharedConstants.C_NICKNAME_KEY
import com.depromeet.bboxx.util.constants.SharedConstants.C_NICKNAME_SHRED

class MyPageActivity: BaseActivity<ActivityMypageBinding>(R.layout.activity_mypage) {

    private val myPageViewModel : MyPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onInit()

    }

    private fun onInit(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            myPageViewModel.version.value = appVersionInfo(this)
        }

        initSharedPreference(this, C_NICKNAME_SHRED)

        binding.clTopView.setBackBtn(object : CustomTopView.OnclickCallback{
            override fun callback() {

            }
        }, resources.getString(R.color.white) )

        myPageViewModel.nickname.value = getDataStringSharedPreference(C_NICKNAME_KEY)
    }
}