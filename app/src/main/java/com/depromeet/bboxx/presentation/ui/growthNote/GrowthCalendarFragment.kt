package com.depromeet.bboxx.presentation.ui.growthNote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.BottomCalendarViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class GrowthCalendarFragment: BottomSheetDialogFragment() {

    companion object {
        val TAG = this::class.java.name

        fun newInstance(): GrowthCalendarFragment {
            return GrowthCalendarFragment()
        }

    }


    private lateinit var binding : BottomCalendarViewBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.bind(
            inflater.inflate(
                R.layout.bottom_calendar_view,
                container,
                false
            )
        )!!

        binding.lifecycleOwner = this

        return binding.root
    }
}