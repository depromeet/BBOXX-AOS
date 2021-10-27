package com.depromeet.bboxx.presentation.ui.feelnote

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.depromeet.bboxx.databinding.EmotionDiaryResultLayoutBinding
import com.depromeet.bboxx.presentation.ui.MainActivity

class FeelingNoteResultFragment(val selectedFeeling: String) : Fragment() {

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

        var isTitleActivated = false
        var isMainActivated = false
        val binding = EmotionDiaryResultLayoutBinding.inflate(inflater, container, false)

        val string = SpannableStringBuilder("오늘 "+selectedFeeling+"로 힘들었어.")

        string.apply{
            setSpan(StyleSpan(Typeface.BOLD), 3, 3+selectedFeeling.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        }
      binding.tvMainTitle01.text =string
        return binding.root
    }



}