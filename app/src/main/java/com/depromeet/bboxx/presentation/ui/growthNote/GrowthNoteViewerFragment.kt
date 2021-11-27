package com.depromeet.bboxx.presentation.ui.growthNote

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.LayoutGrowthNoteViewerBinding
import com.depromeet.bboxx.domain.model.ImprovementDiaries
import com.depromeet.bboxx.presentation.extension.observeNonNull
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.ui.result.Result
import com.depromeet.bboxx.presentation.utils.CustomTopView
import com.depromeet.bboxx.util.DateFormatter
import com.google.android.material.chip.Chip

class GrowthNoteViewerFragment(val bgColor: Int, val improveData: ImprovementDiaries) : Fragment() {

    lateinit var mainActivity: MainActivity
    
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    private lateinit var improveModel: ImprovementDiaries

    @SuppressLint("ClickableViewAccessibility", "ResourceType", "NewApi")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        improveModelInit()

        val binding = LayoutGrowthNoteViewerBinding.inflate(inflater, container, false)

        //  이전 감정 보기
        binding.clSetFold.setOnClickListener {
            if(!binding.clHistory.isVisible){
                mainActivity.searchFeelingContent(improveData.emotionDiaryId)
            }
            else{
                binding.arrowDown.rotation = 270f
                binding.clHistory.visibility = View.GONE
            }
        }

        binding.clTopView.setBackBtn(object : CustomTopView.OnclickCallback {
            override fun callback() {
                mainActivity.clearThisFragment(this@GrowthNoteViewerFragment)
            }

        }, )

        binding.clTopView.setBackgroundColor(ContextCompat.getColor(mainActivity, bgColor))
        mainActivity.setStatusBarColor(bgColor)

        setImproveData(binding, bgColor)

        mainActivity.feelingNoteViewModel.emotionDiary.observeNonNull(this){
            if (it.content.isNotBlank()) {
                binding.arrowDown.rotation = 90f
                binding.clHistory.visibility = View.VISIBLE
                binding.tvDateFeel.text = it.createdAt
                binding.etTitleText.text = it.title
                binding.etMainText.text = it.content

            } else {
                binding.arrowDown.rotation = 270f
                binding.clHistory.visibility = View.GONE
            }
        }

        mainActivity.feelingNoteViewModel.searchNetworkErrorEvent.observeNonNull(this){
            when(it){
                is Result.Error ->{
                    errorEventMsg(it.exception)
                }
            }
        }

        return binding.root
    }

    private fun improveModelInit(){
        improveModel = improveData
    }

    @SuppressLint("NewApi")
    private fun setImproveData(binding: LayoutGrowthNoteViewerBinding, bgColor: Int){
        if(::improveModel.isInitialized){
            improveModel.run {
                binding.clBg.setBackgroundColor(ContextCompat.getColor(requireContext(), bgColor))
                binding.tvDate.text = DateFormatter().formatFormatterEmotion(improveData.createdAt)
                binding.tvTitle.text = improveData.title
                binding.tvMainText.text = improveData.content

                val tagList = arrayListOf<String>()
                tagList.addAll(improveData.tags)
                tagList.forEach {
                    val chip = Chip(context)
                    chip.text = it
                    chip.textSize = 14F
                    chip.setTextColor(Color.parseColor("#ffffff"))
                    chip.chipBackgroundColor =
                        ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray_10))
                    binding.chipGroup.addView(chip)
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        mainActivity.setStatusBarColor(R.color.main_bg)
    }

    private fun errorEventMsg(error: Throwable){
        val errorMsg = error.message
        Toast.makeText(requireContext(), "Error Msg: $errorMsg", Toast.LENGTH_SHORT).show()
    }
}