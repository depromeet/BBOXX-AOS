package com.depromeet.bboxx.presentation.ui.growthNote

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.GrowthDiaryBinding
import com.depromeet.bboxx.presentation.extension.observeNonNull
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.ui.feelhistory.FeelingHistoryFragment
import com.depromeet.bboxx.presentation.ui.mypage.MyPageFragment
import com.depromeet.bboxx.presentation.utils.CustomTopView
import com.depromeet.bboxx.util.DateFormatter
import com.depromeet.bboxx.util.SharedPreferenceUtil.getDataIntSharedPreference
import com.depromeet.bboxx.util.SharedPreferenceUtil.initSharedPreference
import com.depromeet.bboxx.util.constants.SharedConstants.C_MEMBER_ID_KEY
import com.depromeet.bboxx.util.constants.SharedConstants.C_MEMBER_ID_SHRED

class GrowthNoteFragment : Fragment(), GrowthMonthListener {

    lateinit var mainActivity: MainActivity
    lateinit var mAdapter: CardViewAdapter
    private var nowYear: String = ""
    private var nowMonth: String = ""
    private lateinit var binding: GrowthDiaryBinding
    private var standardCurrentDate = ""
    private var currentDate = ""
    private var isLeftMoveToEventStatus = true
    private var beforeMonth = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    @SuppressLint("ClickableViewAccessibility", "ResourceType", "NewApi")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.bind(
                inflater.inflate(
                    R.layout.growth_diary,
                    container,
                    false
                )
            )!!

        setDateInit()

        binding.clTopView.setRightBtn(object : CustomTopView.OnclickCallback {
            override fun callback() {
                mainActivity.addFragment(MyPageFragment())
            }
        }, R.drawable.ic_profile)

        mAdapter = CardViewAdapter(mainActivity)

        binding.rlCardView.run {
            adapter = mAdapter
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        //  날짜 데이터 클릭 시 버텀 달력 띄
        binding.tvMonth.setOnClickListener {
            val monthList = arrayListOf<String>(nowMonth)

            GrowthCalendarFragment.newInstance(this, monthList, nowYear, currentDate)
                .show(childFragmentManager, GrowthCalendarFragment.TAG)

        }
        //  이전 달
        binding.ivLeft.setOnClickListener {
            moveLeftDate()
        }

        //   다음 달 이동
        binding.ivRight.setOnClickListener {
            //moveRightDate()
            onTest()
        }

        if (binding.btnShow.isVisible) {
            binding.btnShow.setOnClickListener {
                mainActivity.addFragment(FeelingHistoryFragment())
            }
        }

        //  현재 날짜 기준으로 성장일기 요청
        mainActivity.getGrowthList(nowYear, nowMonth)

        return binding.root

    }

    override fun onResume() {
        super.onResume()
        mainActivity.setStatusBarColor(R.color.main_bg)

        if(mainActivity.fcmTitle.isNotBlank()){
            mainActivity.addFragment(FeelingHistoryFragment())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity.growthNoteViewModel.growthList.observeNonNull(this) {
            if (it.isNotEmpty()) {
                binding.emptyView.isVisible = false
                binding.rlCardView.isVisible = true
                mAdapter.setData(it)
            } else {
                binding.rlCardView.isVisible = false
                binding.emptyView.isVisible = true
            }
        }
    }

    @SuppressLint("NewApi")
    private fun setDateInit() {
        currentDate = DateFormatter().calendarNowTime()

        standardCurrentDate = currentDate
        nowYear = DateFormatter().formatNowYear()
        nowMonth = DateFormatter().formatNowMonth()
        beforeMonth = nowMonth

        binding.tvMonth.text = currentDate

        //  2021.11.13일 출시 기준으로 배포되기 때문에 이전 달 이동은 버튼은 클릭 비활성화
        setLeftImageButtonUnActive()
    }

    @SuppressLint("NewApi")
    private fun moveLeftDate() {
        if (!isLeftMoveToEventStatus) {
            if (currentDate == standardCurrentDate) {
                setLeftImageButtonUnActive()
            } else {
                currentDate = DateFormatter().growthCalendarMinerTime(currentDate)

                nowYear = currentDate.substring(0, 4)
                nowMonth = currentDate.substring(6, 8)

                binding.tvMonth.text = currentDate

                //  날짜 기준으로 성장일기 요청
                mainActivity.getGrowthList(nowYear, nowMonth)

                if (currentDate == standardCurrentDate) {
                    setLeftImageButtonUnActive()
                    isLeftMoveToEventStatus = true
                }
            }
        }
    }

    @SuppressLint("NewApi")
    private fun moveRightDate() {
        isLeftMoveToEventStatus = false
        currentDate = DateFormatter().growthCalendarAddTime(currentDate)

        nowYear = currentDate.substring(0, 4)
        nowMonth = currentDate.substring(6, 8)

        binding.tvMonth.text = currentDate

        setLeftImageButtonActive()

        //  날짜 기준으로 성장일기 요청
        mainActivity.getGrowthList(nowYear, nowMonth)
    }

    private fun setLeftImageButtonActive() {
        binding.ivRight.backgroundTintList =
            ColorStateList.valueOf(Color.parseColor("#2C2C2C"))
        binding.ivLeft.backgroundTintList =
            ColorStateList.valueOf(Color.parseColor("#2C2C2C"))
        binding.ivLeft.isClickable = true
    }

    private fun setLeftImageButtonUnActive() {
        binding.ivLeft.backgroundTintList =
            ColorStateList.valueOf(Color.parseColor("#9D9D9D"))
        binding.ivLeft.isClickable = false
    }

    override fun clickMonth(year: String, month: String) {
        binding.tvMonth.text = "${year}년 ${month}월"
        currentDate = "${year}년 ${month}월"
        nowYear = year
        nowMonth = month

        if (currentDate != standardCurrentDate) {
            setLeftImageButtonActive()
            isLeftMoveToEventStatus = false
        }

        mainActivity.getGrowthList(year, month)
    }

    /**
     *  Notification Send Logic Test Code
     */
    private fun onTest() {
        // Test Notification Send Logic
        initSharedPreference(requireContext(), C_MEMBER_ID_SHRED)
        val memberId = getDataIntSharedPreference(C_MEMBER_ID_KEY)

//        mainActivity.growthNoteViewModel.testSendNotification(6, memberId!!)
//        mainActivity.growthNoteViewModel.testSendNotification(7, memberId!!)
        mainActivity.growthNoteViewModel.testSendNotification(9, memberId!!)
    }
}