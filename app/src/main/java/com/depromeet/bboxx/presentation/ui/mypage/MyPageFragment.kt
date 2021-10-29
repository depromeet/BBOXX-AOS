package com.depromeet.bboxx.presentation.ui.mypage

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.ActivityMypageBinding
import com.depromeet.bboxx.presentation.base.BaseFragment
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.utils.CustomTopView
import com.depromeet.bboxx.presentation.viewmodel.MyPageViewModel
import com.depromeet.bboxx.util.SharedPreferenceUtil
import com.depromeet.bboxx.util.VersionUtils
import com.depromeet.bboxx.util.constants.SharedConstants
import javax.inject.Inject

//  프래그먼트로 전환 예정
class MyPageFragment @Inject constructor() : BaseFragment<ActivityMypageBinding>(R.layout.activity_mypage) {

    lateinit var mainActivity: MainActivity
    private val myPageViewModel : MyPageViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInit()
        // titlebar 검은색으로 수정 필요
    }

    private fun onInit(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            myPageViewModel.version.value = VersionUtils.appVersionInfo(requireContext())
        }

        SharedPreferenceUtil.initSharedPreference(requireContext(), SharedConstants.C_NICKNAME_SHRED)

        binding.clTopView.setBackBtn(object : CustomTopView.OnclickCallback{
            override fun callback() {

            }
        }, resources.getString(R.color.white) )

        myPageViewModel.nickname.value =
            SharedPreferenceUtil.getDataStringSharedPreference(SharedConstants.C_NICKNAME_KEY)
    }

}