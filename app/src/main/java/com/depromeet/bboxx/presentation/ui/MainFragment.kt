package com.depromeet.bboxx.presentation.ui

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.FragmentMainBinding
import com.depromeet.bboxx.presentation.base.BaseFragment
import com.depromeet.bboxx.presentation.extension.observeNonNull
import com.depromeet.bboxx.presentation.ui.feelhistory.FeelingHistoryFragment
import com.depromeet.bboxx.presentation.ui.main.SelectActionFragment
import com.depromeet.bboxx.presentation.ui.mypage.MyPageFragment
import com.depromeet.bboxx.presentation.utils.CustomTopView
import com.depromeet.bboxx.util.DateFormatter
import com.depromeet.bboxx.util.SharedPreferenceUtil.getDataIntSharedPreference
import com.depromeet.bboxx.util.SharedPreferenceUtil.getDataStringSharedPreference
import com.depromeet.bboxx.util.SharedPreferenceUtil.initSharedPreference
import com.depromeet.bboxx.util.constants.SharedConstants.C_MEMBER_ID_KEY
import com.depromeet.bboxx.util.constants.SharedConstants.C_MEMBER_ID_SHRED
import com.depromeet.bboxx.util.constants.SharedConstants.C_NICKNAME_KEY
import com.depromeet.bboxx.util.constants.SharedConstants.C_NICKNAME_SHRED
import javax.inject.Inject

class MainFragment @Inject constructor() : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    lateinit var mainActivity: MainActivity

    private var nickName = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onResume() {
        super.onResume()
        if(mainActivity.fcmTitle.isNotBlank()){
            mainActivity.addFragment(FeelingHistoryFragment())
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity.setStatusBarColor(R.color.main_bg)

        val today = DateFormatter().formatNowTime()
        binding.txtTodayDate.text = today

        initSharedPreference(requireContext(), C_NICKNAME_SHRED)
        nickName = getDataStringSharedPreference(C_NICKNAME_KEY) ?: ""

        initSharedPreference(requireContext(), C_MEMBER_ID_SHRED)
        val memberId = getDataIntSharedPreference(C_MEMBER_ID_KEY).toString()

        mainActivity.mainViewModel.nickName.observeNonNull(this){
            if(it.isNotBlank()){
                nickName = it
            }

            binding.txtNicknameTitle.text = getString(R.string.text_main_nickname_title, nickName)
        }


        binding.clTopView.setRightBtn(object : CustomTopView.OnclickCallback{
            override fun callback() {
                mainActivity.addFragment(MyPageFragment())
            }
        }, R.drawable.ic_profile)

        binding.layGoToFeelingNote.setOnClickListener {
            Log.d("okhttpClient","GoToHistory")
            mainActivity.addFragment(FeelingHistoryFragment())
        }

        binding.layGoToDecibel.setOnClickListener {
            Log.d("okhttpClient","GoToSelect")
            mainActivity.addFragment(SelectActionFragment())
        }
    }
}