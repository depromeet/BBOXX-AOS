package com.depromeet.bboxx.presentation.ui.growthNote

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.depromeet.bboxx.databinding.LayoutDeleteAllBinding
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class GrowthNoteDeleteAll(val emotionId: Int) : BottomSheetDialogFragment() {

    lateinit var mainActivity: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = LayoutDeleteAllBinding.inflate(inflater, container, false)


        initView(binding)
        return binding.root
    }


    private fun initView(binding: LayoutDeleteAllBinding) {

        binding.clBack.setOnClickListener {
            mainActivity.clearThisFragment(this)
        }

        binding.btnDeleteAll.setOnClickListener {
            mainActivity.deleteFeelData(emotionId)
            this.dismiss()
            mainActivity.addFragment(GrowthNoteDeleteCompletelyFragment())
        }
        binding.btnCancle.setOnClickListener {
            this.dismiss()
        }

    }
}