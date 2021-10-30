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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.bboxx.databinding.EmotionDiaryResultLayoutBinding
import com.depromeet.bboxx.presentation.ui.MainActivity

class FeelingNoteResultFragment(val selectedFeeling: String, val feelingList : ArrayList<FeelingNoteSelectFeelingFragment.tempFeeling>, val title : String, val main : String ) : Fragment() {

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

        val binding = EmotionDiaryResultLayoutBinding.inflate(inflater, container, false)

        binding.rlFeeling.adapter = FeelingSelectedAdapter(mainActivity).apply {
            setData(feelingList)
        }

        val layoutManager = LinearLayoutManager(mainActivity)
        layoutManager.orientation = RecyclerView.HORIZONTAL
        binding.rlFeeling.layoutManager = layoutManager
        val string = SpannableStringBuilder("오늘 "+selectedFeeling+"로 힘들었어.")
        binding.tvMainTitle02.text = title
        binding.tvSubText.text = main

        string.apply{
            setSpan(StyleSpan(Typeface.BOLD), 3, 3+selectedFeeling.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        }

        binding.btnSuccess.setOnClickListener {
            mainActivity.addFragment(FeelingNoteCompleteFragment())
        }
      binding.tvMainTitle01.text =string
        return binding.root
    }



}