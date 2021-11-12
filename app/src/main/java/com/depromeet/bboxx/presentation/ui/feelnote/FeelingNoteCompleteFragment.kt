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



}