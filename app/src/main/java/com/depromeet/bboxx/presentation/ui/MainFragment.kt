package com.depromeet.bboxx.presentation.ui

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.FragmentMainBinding
import com.depromeet.bboxx.presentation.base.BaseFragment
import com.depromeet.bboxx.presentation.ui.feelhistory.FeelingHistoryFragment
import com.depromeet.bboxx.presentation.ui.main.SelectActionFragment
import com.depromeet.bboxx.presentation.ui.mypage.MyPageFragment
import com.depromeet.bboxx.presentation.utils.CustomTopView
import com.depromeet.bboxx.util.DateFormatter
import com.depromeet.bboxx.util.SharedPreferenceUtil
import com.depromeet.bboxx.util.SharedPreferenceUtil.initSharedPreference
import com.depromeet.bboxx.util.constants.SharedConstants.C_NICKNAME_KEY
import com.depromeet.bboxx.util.constants.SharedConstants.C_NICKNAME_SHRED
import javax.inject.Inject

class MainFragment @Inject constructor() : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO HAERIN FRAGMNET이동
        initSharedPreference(mainActivity.applicationContext, C_NICKNAME_SHRED)
        val nickName = SharedPreferenceUtil.getDataStringSharedPreference(C_NICKNAME_KEY)
        binding.txtNicknameTitle.text = getString(R.string.text_main_nickname_title, nickName)
        val today = DateFormatter().formatNowTime()
        binding.txtTodayDate.text = today

        binding.clTopView.setRightBtn(object : CustomTopView.OnclickCallback{
            override fun callback() {
                mainActivity.addFragment(MyPageFragment())
            }
        }, R.drawable.ic_profile)

        binding.layGoToFeelingNote.setOnClickListener {
            mainActivity.addFragment(FeelingHistoryFragment())
        }

        binding.layGoToDecibel.setOnClickListener {
            mainActivity.addFragment(SelectActionFragment())
        }

    }

}