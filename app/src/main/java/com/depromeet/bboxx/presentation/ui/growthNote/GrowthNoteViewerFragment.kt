package com.depromeet.bboxx.presentation.ui.growthNote

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.LayoutGrowthNoteViewerBinding
import com.depromeet.bboxx.presentation.extension.observeNonNull
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.utils.CustomTopView
import com.depromeet.bboxx.util.DateFormatter

class GrowthNoteViewerFragment(val bgColor: Int, val emotionDiaryId: Int) : Fragment() {

    lateinit var mainActivity: MainActivity


    var isFold = true
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }


    @SuppressLint("ClickableViewAccessibility", "ResourceType", "NewApi")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = LayoutGrowthNoteViewerBinding.inflate(inflater, container, false)

        //  이전 감정 보기
        binding.clSetFold.setOnClickListener {
            mainActivity.beforeFeelingContent(emotionDiaryId)
        }

        binding.clTopView.setBackBtn(object : CustomTopView.OnclickCallback {
            override fun callback() {
                mainActivity.clearThisFragment(this@GrowthNoteViewerFragment)
            }

        }, resources.getString(R.color.white))

        binding.clTopView.setBackgroundColor(ContextCompat.getColor(mainActivity, bgColor))

        mainActivity.setStatusBarColor(bgColor)

        mainActivity.feelingNoteViewModel.emotionDiary.observeNonNull(this){
            if (it.content.isNotBlank()) {
                binding.arrowDown.rotation = 90f
                binding.clHistory.visibility = View.VISIBLE
                binding.tvDateFeel.text = DateFormatter().formatFormatterEmotion(it.createdAt)
                binding.etTitleText.text = it.title
                binding.etMainText.text = it.content
            } else {
                binding.arrowDown.rotation = 270f
                binding.clHistory.visibility = View.GONE
            }
        }

        return binding.root
    }


}