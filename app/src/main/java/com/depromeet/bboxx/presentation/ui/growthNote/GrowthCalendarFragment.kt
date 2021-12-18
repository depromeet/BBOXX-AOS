package com.depromeet.bboxx.presentation.ui.growthNote

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.BottomCalendarViewBinding
import com.depromeet.bboxx.presentation.model.SelectCalendarModel
import com.depromeet.bboxx.util.DateFormatter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class GrowthCalendarFragment: BottomSheetDialogFragment() {

    companion object {
        val TAG = this::class.java.name

        fun newInstance(listener: GrowthMonthListener,  month: List<String>, year: String, currentDate: String): GrowthCalendarFragment {
            return GrowthCalendarFragment().apply{
                this.monthListener = listener
                this.yearTitle = year
                this.usingMonth = month as MutableList<String>
                this.currentDate = currentDate
            }
        }
    }

    private lateinit var binding : BottomCalendarViewBinding
    private var yearTitle: String = ""
    private var currentDate: String = ""
    private var standardCurrentDate: String = ""

    private var usingMonth = mutableListOf<String>()
    private var calendarList = mutableListOf<SelectCalendarModel>()
    private var monthListener: GrowthMonthListener? = null
    private var isLeftMoveToEventStatus = true

    private val growthCalendarAdapter: GrowthCalendarAdapter by lazy{
        GrowthCalendarAdapter({
            monthListener?.run {
                clickMonth(yearTitle, it)
            }
            dismiss()
        },{ isClicked ->
            // 클릭 안되는거에 대한 예외 처리 필요
        })
    }

    /**
     *  구현 필요한거 왼쪽, 오른쪽 갈수 있게 로직 구현
     */
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

        standardCurrentDate = currentDate

        onViewSetting()

        return binding.root
    }


    private fun onViewSetting(){
//        binding.btnCalLeft.backgroundTintList =
//            ColorStateList.valueOf(Color.parseColor("#9D9D9D"))

        binding.txtYear.text = yearTitle

        setAdapterInit()

        binding.btnCalLeft.setOnClickListener {
            moveLeftDate()
        }

        binding.btnCalRight.setOnClickListener {
            moveRightDate()
        }
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
        setLeftImageButtonActive()
    }

    private fun setCalendarModel(){
        for(month in 1..12){
            calendarActive(month)
        }
    }

    @SuppressLint("NewApi")
    private fun moveLeftDate(){
        currentDate = DateFormatter().calendarMinerYear(currentDate)

        yearTitle = currentDate.substring(0,4)

        binding.txtYear.text = yearTitle
    }

    @SuppressLint("NewApi")
    private fun moveRightDate(){
        currentDate = DateFormatter().calendarAddYear(currentDate)

        yearTitle = currentDate.substring(0,4)

        binding.txtYear.text = yearTitle
    }

    private fun setLeftImageButtonActive() {
        binding.btnCalLeft.backgroundTintList =
            ColorStateList.valueOf(Color.parseColor("#2C2C2C"))
        binding.btnCalRight.backgroundTintList =
            ColorStateList.valueOf(Color.parseColor("#2C2C2C"))
        binding.btnCalLeft.isClickable = true
    }

    private fun setLeftImageButtonUnActive(){
        binding.btnCalLeft.backgroundTintList =
            ColorStateList.valueOf(Color.parseColor("#9D9D9D"))
        binding.btnCalLeft.isClickable = false
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