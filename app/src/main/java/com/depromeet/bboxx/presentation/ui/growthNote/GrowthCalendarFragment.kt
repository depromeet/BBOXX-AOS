package com.depromeet.bboxx.presentation.ui.growthNote

import android.app.Dialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.BottomCalendarViewBinding
import com.depromeet.bboxx.presentation.model.SelectCalendarModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class GrowthCalendarFragment(val itemClick: (String)-> Unit): BottomSheetDialogFragment() {

    companion object {
        val TAG = this::class.java.name

        fun newInstance(month: List<String>, year: String): GrowthCalendarFragment {
            return GrowthCalendarFragment {
                it
            }.apply {
                this.yearTitle = year
                this.usingMonth = month as MutableList<String>
            }
        }
    }

    private lateinit var binding : BottomCalendarViewBinding
    private var yearTitle: String = ""
    private var usingMonth = mutableListOf<String>()
    private var calendarList = mutableListOf<SelectCalendarModel>()

    private val growthCalendarAdapter: GrowthCalendarAdapter by lazy{
        GrowthCalendarAdapter({
            itemClick(it)
            dismiss()
        },{ isClicked ->
            // 클릭 안되는거에 대한 예외 처리 필요
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

        binding = DataBindingUtil.bind(
            inflater.inflate(
                R.layout.bottom_calendar_view,
                container,
                false
            )
        )!!


        binding.lifecycleOwner = this

        onViewSetting()

        return binding.root
    }


    private fun onViewSetting(){
        binding.btnCalLeft.backgroundTintList =
            ColorStateList.valueOf(Color.parseColor("#9D9D9D"))

        binding.txtYear.text = yearTitle

        setAdapterInit()
    }

    private fun setAdapterInit(){
        growthCalendarAdapter.notifyDataSetChanged()

        val numberOfColumns = 4
        binding.rvCalendar.layoutManager = GridLayoutManager(requireContext(), numberOfColumns)

        binding.rvCalendar.run {
            adapter = growthCalendarAdapter
            setHasFixedSize(true)
        }
        setCalendarModel()

        growthCalendarAdapter.setDataList(calendarList)

    }

    private fun setCalendarModel(){
        for(month in 1..12){
            if(usingMonth.contains(month.toString())){
                calendarActive(month)
            }
            else{
                calendarList.add(SelectCalendarModel(
                    "$month 월",
                    R.color.color_E5E5E5,
                    R.color.color_9D9D9D,
                    false,
                    month
                ))
            }
        }
    }

    private fun calendarActive(month: Int){
        when (month) {
            1 -> {
                calendarList.add(
                    SelectCalendarModel(
                        "$month 월",
                        R.color.card_view_1,
                        R.color.white,
                        true,
                        month
                    ))
            }
            2 -> {
                calendarList.add(
                    SelectCalendarModel(
                        "$month 월",
                        R.color.card_view_2,
                        R.color.white,
                        true,
                        month
                    ))
            }
            3 -> {
                calendarList.add(
                    SelectCalendarModel(
                        "$month 월",
                        R.color.card_view_3,
                        R.color.white,
                        true,
                        month
                    ))
            }
            4 -> {
                calendarList.add(
                    SelectCalendarModel(
                        "$month 월",
                        R.color.card_view_4,
                        R.color.white,
                        true,
                        month
                    ))
            }
            5 -> {
                calendarList.add(
                    SelectCalendarModel(
                        "$month 월",
                        R.color.card_view_5,
                        R.color.white,
                        true,
                        month
                    ))
            }
            6 -> {
                calendarList.add(
                    SelectCalendarModel(
                        "$month 월",
                        R.color.card_view_6,
                        R.color.white,
                        true,
                        month
                    ))
            }
            7 -> {
                calendarList.add(
                    SelectCalendarModel(
                        "$month 월",
                        R.color.card_view_7,
                        R.color.white,
                        true,
                        month
                    ))
            }
            8 -> {
                calendarList.add(
                    SelectCalendarModel(
                        "$month 월",
                        R.color.card_view_8,
                        R.color.white,
                        true,
                        month
                    ))
            }
            9 -> {
                calendarList.add(
                    SelectCalendarModel(
                        "$month 월",
                        R.color.card_view_9,
                        R.color.white,
                        true,
                        month
                    ))
            }
            10 -> {
                calendarList.add(
                    SelectCalendarModel(
                        "$month 월",
                        R.color.card_view_10,
                        R.color.white,
                        true,
                        month
                    ))
            }
            11 -> {
                calendarList.add(
                    SelectCalendarModel(
                        "$month 월",
                        R.color.card_view_11,
                        R.color.white,
                        true,
                        month
                    ))
            }
            12 -> {
                calendarList.add(
                    SelectCalendarModel(
                        "$month 월",
                        R.color.card_view_12,
                        R.color.white,
                        true,
                        month
                    ))
            }
        }
    }


}