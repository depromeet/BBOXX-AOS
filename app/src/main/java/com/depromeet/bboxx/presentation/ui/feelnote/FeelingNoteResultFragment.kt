package com.depromeet.bboxx.presentation.ui.feelnote

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.View
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.EmotionDiaryResultLayoutBinding
import com.depromeet.bboxx.presentation.base.BaseFragment
import com.depromeet.bboxx.presentation.ui.MainActivity

class FeelingNoteResultFragment(val selectedFeeling: String) : BaseFragment<EmotionDiaryResultLayoutBinding>(
    R.layout.emotion_diary_result_layout) {

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var isTitleActivated = false
        var isMainActivated = false

        val string = SpannableStringBuilder("오늘 "+selectedFeeling+"로 힘들었어.")

        string.apply{
            setSpan(StyleSpan(Typeface.BOLD), 3, 3+selectedFeeling.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        binding.tvMainTitle01.text =string
        // categoryId: Int, content: String, emotionStatuses: String, memberId: Int, title: String
        binding.btnSuccess.setOnClickListener {
//            mainActivity.feelingNoteVieModel.writeFeeling(1,
//            "content","dd", daf, )
        }
    }

}