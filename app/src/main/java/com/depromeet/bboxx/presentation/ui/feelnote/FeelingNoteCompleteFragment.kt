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
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.EmotionDiaryResultLayoutBinding
import com.depromeet.bboxx.databinding.FragmentResultBinding
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.ui.MainFragment
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
//                TODO("Not yet implemented")
            }
        }, R.drawable.ic_close)

        binding.btGoToDecibel.setOnClickListener {
            mainActivity.addFragment(DecibelFragment())
        }
        binding.btGoToHome.setOnClickListener {
            mainActivity.addFragment(MainFragment())
        }


        return binding.root
    }



}