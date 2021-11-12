package com.depromeet.bboxx.presentation.ui.mypage

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.ActivityMypageBinding
import com.depromeet.bboxx.presentation.base.BaseFragment
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.utils.CustomTopView
import com.depromeet.bboxx.presentation.viewmodel.MyPageViewModel
import com.depromeet.bboxx.util.SharedPreferenceUtil
import com.depromeet.bboxx.util.SharedPreferenceUtil.getDataBooleanSharedPreference
import com.depromeet.bboxx.util.SharedPreferenceUtil.getDataStringSharedPreference
import com.depromeet.bboxx.util.SharedPreferenceUtil.initSharedPreference
import com.depromeet.bboxx.util.VersionUtils
import com.depromeet.bboxx.util.VersionUtils.appVersionInfo
import com.depromeet.bboxx.util.constants.SharedConstants
import com.depromeet.bboxx.util.constants.SharedConstants.C_PUSH_STATUS_KEY
import com.depromeet.bboxx.util.constants.SharedConstants.C_PUSH_STATUS_SHRED
import javax.inject.Inject


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
    }

    @SuppressLint("NewApi")
    private fun onInit(){

        mainActivity.setStatusBarColor(R.color.mypage_bg)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            myPageViewModel.version.value = VersionUtils.appVersionInfo(requireContext())
        }

        initSharedPreference(
            mainActivity.applicationContext,
            SharedConstants.C_NICKNAME_SHRED
        )
        val nickName = getDataStringSharedPreference(SharedConstants.C_NICKNAME_KEY)

        binding.clTopView.setBackBtn(object : CustomTopView.OnclickCallback{
            override fun callback() {
                mainActivity.clearThisFragment(this@MyPageFragment)
            }
        }, resources.getString(R.color.white) )

        binding.imgBgBoard.setOnClickListener {
            Toast.makeText(requireContext(),"아직 준비 중입니다.",Toast.LENGTH_SHORT).show()
        }

        binding.txtNickname.text = nickName
        binding.txtAppVersion.text = "ver. ${appVersionInfo(requireContext())}"

        initSharedPreference(requireContext(), C_PUSH_STATUS_SHRED)
        val pushStatus = getDataBooleanSharedPreference(C_PUSH_STATUS_KEY)

    }

}