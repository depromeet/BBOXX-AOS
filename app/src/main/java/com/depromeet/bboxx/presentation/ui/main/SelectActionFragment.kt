package com.depromeet.bboxx.presentation.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.FragmentSelectActionBinding
import com.depromeet.bboxx.presentation.base.BaseFragment
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.ui.decibel.DecibelFragment
import com.depromeet.bboxx.presentation.ui.feelnote.FeelingNoteSelectFragment
import com.depromeet.bboxx.presentation.ui.growthNote.GrowthNoteReViewFeelingNote
import com.depromeet.bboxx.presentation.ui.mypage.MyPageFragment
import com.depromeet.bboxx.presentation.utils.CustomTopView
import javax.inject.Inject

class SelectActionFragment @Inject constructor()
    :BaseFragment<FragmentSelectActionBinding>(R.layout.fragment_select_action){

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
    override fun onResume() {
        super.onResume()

        mainActivity.setStatusBarColor(R.color.select_bg)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity.setStatusBarColor(R.color.select_bg)

        binding.btnDecibel.setOnClickListener {
            Log.d("okhttpClient","GoToDecibel")
            mainActivity.addFragment(DecibelFragment(1))
        }

        binding.btnFeel.setOnClickListener {
            Log.d("okhttpClient","GoToSelect")
           mainActivity.addFragment(FeelingNoteSelectFragment(0))
        }

        binding.clTopView.setBackBtn(object : CustomTopView.OnclickCallback{
            override fun callback() {
                mainActivity.clearThisFragment(this@SelectActionFragment)
            }
        }, resources.getString(R.color.white) )

        binding.clTopView.setRightBtn(object : CustomTopView.OnclickCallback{
            override fun callback() {
                mainActivity.addFragment(MyPageFragment())
            }

        }, R.drawable.ic_profile)
    }

    override fun onStop() {
        super.onStop()
        mainActivity.setStatusBarColor(R.color.main_bg)
    }
}