package com.depromeet.bboxx.presentation.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.FragmentSelectActionBinding
import com.depromeet.bboxx.presentation.base.BaseFragment
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.ui.decibel.DecibelFragment
import com.depromeet.bboxx.presentation.ui.feelnote.FeelingNoteSelectFragment
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnDecibel.setOnClickListener {
            mainActivity.addFragment(DecibelFragment())
        }

        binding.btnFeel.setOnClickListener {
            mainActivity.addFragment(FeelingNoteSelectFragment())
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
}