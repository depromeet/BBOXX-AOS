package com.depromeet.bboxx.presentation.ui.feelnote

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.FragmentResultBinding
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.ui.decibel.DecibelFragment
import com.depromeet.bboxx.presentation.utils.CustomTopView
import com.depromeet.bboxx.util.SharedPreferenceUtil
import com.depromeet.bboxx.util.constants.SharedConstants

class FeelingNoteCompleteFragment() : Fragment() {

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentResultBinding.inflate(inflater, container, false)

        SharedPreferenceUtil.initSharedPreference(
            requireContext(),
            SharedConstants.C_NICKNAME_SHRED
        )
        val nickName = SharedPreferenceUtil.getDataStringSharedPreference(SharedConstants.C_NICKNAME_KEY)
            ?: ""
        binding.tvMainText.text = getString(R.string.text_feeling_result_nickname, nickName)
        binding.clTopView.setRightBtn(object  : CustomTopView.OnclickCallback{
            override fun callback() {
                mainActivity.allClearFragment()
            }
        }, R.drawable.ic_close)

        binding.btGoToDecibel.setOnClickListener {
            mainActivity.addFragment(DecibelFragment())
        }
        binding.btGoToHome.setOnClickListener {
            mainActivity.allClearFragment()
        }


        return binding.root
    }

    override fun onStop() {
        super.onStop()
        mainActivity.setStatusBarColor(R.color.select_bg)
    }

}