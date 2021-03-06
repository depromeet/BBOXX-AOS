package com.depromeet.bboxx.presentation.ui.growthNote

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.FragmentDeleteAllResultBinding
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.utils.CustomTopView

class GrowthNoteDeleteCompletelyFragment() : Fragment() {

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

        val binding = FragmentDeleteAllResultBinding.inflate(inflater, container, false)

        binding.clTopView.setRightBtn(object : CustomTopView.OnclickCallback {
            override fun callback() {
//                mainActivity.finish()
//                NavigatorUI.toMain(mainActivity)
                mainActivity.allClearFragment()
            }
        }, R.drawable.ic_close)


        binding.btGoToHome.setOnClickListener {
//            mainActivity.finish()
//            NavigatorUI.toMain(mainActivity)
              mainActivity.allClearFragment()
             // mainActivity.allClearAndGrowth()
        }


        return binding.root
    }


}