package com.depromeet.bboxx.presentation.ui.growthNote

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.BottomCalendarViewBinding
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class GrowthCalendarFragment: BottomSheetDialogFragment() {

    companion object {
        val TAG = this::class.java.name

        fun newInstance(): GrowthCalendarFragment {
            return GrowthCalendarFragment()
        }

    }

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

        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

        val binding = BottomCalendarViewBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            val bottomSheet = dialog.findViewById<FrameLayout>(R.id.design_bottom_sheet)
            bottomSheet?.post {
                val behavior = BottomSheetBehavior.from(bottomSheet)

                behavior.setState(BottomSheetBehavior.STATE_EXPANDED)
            }
        }

        return dialog
    }

}