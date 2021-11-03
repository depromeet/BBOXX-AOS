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
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.utils.CustomTopView

class GrowthNoteViewerFragment(val bgColor: Int) : Fragment() {

    lateinit var mainActivity: MainActivity


    var isFold = true
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }


    @SuppressLint("ClickableViewAccessibility", "ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = LayoutGrowthNoteViewerBinding.inflate(inflater, container, false)

        binding.clSetFold.setOnClickListener {
            if (isFold) {

                binding.arrowDown.rotation = 90f
                binding.clHistory.visibility = View.VISIBLE

            } else {
                binding.arrowDown.rotation = 270f
                binding.clHistory.visibility = View.GONE
            }
            isFold = !isFold

        }
        binding.clTopView.setBackBtn(object : CustomTopView.OnclickCallback {
            override fun callback() {
                mainActivity.clearThisFragment(this@GrowthNoteViewerFragment)
            }

        }, resources.getString(R.color.white))

        binding.svViewer.setBackgroundColor(ContextCompat.getColor(mainActivity, bgColor))

        mainActivity.setStatusBarColor(bgColor)
        return binding.root
    }

}