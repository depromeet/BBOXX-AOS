package com.depromeet.bboxx.presentation.ui.growthNote

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.depromeet.bboxx.databinding.GrowthFeelingNoteLayoutBinding
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.utils.CustomTopView

class GrowthNoteReViewFeelingNote() : Fragment() {

    lateinit var mainActivity: MainActivity


    var a = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }


    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = GrowthFeelingNoteLayoutBinding.inflate(inflater, container, false)


        binding.clTopView.setBackBtn(object :CustomTopView.OnclickCallback{
            override fun callback() {
                mainActivity.clearThisFragment(this@GrowthNoteReViewFeelingNote)
            }
        })

        val emotionDiaryId = 0
        binding.btnSuccess.setOnClickListener {
            mainActivity.addTopFragment(GrwothNoteTagFragment(emotionDiaryId))
        }

        return binding.root
    }


}