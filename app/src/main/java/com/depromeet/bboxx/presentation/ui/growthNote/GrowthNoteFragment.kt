package com.depromeet.bboxx.presentation.ui.growthNote

import android.annotation.SuppressLint
import android.content.Context
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
import com.depromeet.bboxx.domain.model.ImprovementDiaries
import com.depromeet.bboxx.presentation.extension.observeNonNull
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.ui.feelhistory.FeelingHistoryFragment
import com.depromeet.bboxx.presentation.ui.mypage.MyPageFragment
import com.depromeet.bboxx.presentation.utils.CardViewItemDecoration
import com.depromeet.bboxx.presentation.utils.CustomTopView
import com.depromeet.bboxx.util.DateFormatter
import com.depromeet.bboxx.util.SharedPreferenceUtil.getDataIntSharedPreference
import com.depromeet.bboxx.util.SharedPreferenceUtil.initSharedPreference
import com.depromeet.bboxx.util.constants.SharedConstants.C_MEMBER_ID_KEY
import com.depromeet.bboxx.util.constants.SharedConstants.C_MEMBER_ID_SHRED

class GrowthNoteFragment : Fragment(), GrowthMonthListener{

    lateinit var mainActivity: MainActivity
    lateinit var mAdapter: CardViewAdapter
    private var nowYear: String = ""
    private var nowMonth: String = ""
    private lateinit var binding : GrowthDiaryBinding
    var a = 0

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

        nowYear = DateFormatter().formatNowYear()
        nowMonth = DateFormatter().formatNowMonth()

        binding =
            DataBindingUtil.bind(
                inflater.inflate(
                    R.layout.growth_diary,
                    container,
                    false
                )
            )!!


        binding.clTopView.setRightBtn(object : CustomTopView.OnclickCallback {
            override fun callback() {
                mainActivity.addFragment(MyPageFragment())
            }
        }, R.drawable.ic_profile)

        mAdapter = CardViewAdapter(mainActivity)

        binding.tvMonth.text = DateFormatter().calendarNowTime()

        binding.rlCardView.run {
            adapter = mAdapter
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        if (binding.btnShow.isVisible) {
            binding.btnShow.setOnClickListener {
                mainActivity.addFragment(FeelingHistoryFragment())
            }
        }

        mainActivity.getGrowthList(nowYear, nowMonth)

        binding.tvMonth.setOnClickListener {

            val monthList = arrayListOf<String>(nowMonth)
            nowYear = DateFormatter().formatNowYear()

            GrowthCalendarFragment.newInstance(this, monthList, nowYear)
                .show(childFragmentManager, GrowthCalendarFragment.TAG)

        }

        val dataList1 = ArrayList<ImprovementDiaries>()
        setCardView(binding, dataList1)
        binding.ivLeft.setOnClickListener {
            a++
            if (a % 2 == 1) {
                // setCardView(binding, dataList)
            } else {
                setCardView(binding, dataList1)

            }
        }

        binding.ivRight.setOnClickListener {
            initSharedPreference(requireContext(), C_MEMBER_ID_SHRED)
            val memberId = getDataIntSharedPreference(C_MEMBER_ID_KEY)

            mainActivity.growthNoteViewModel.testSendNotification(1, memberId!!)
        }


        return binding.root

    }

    override fun onResume() {
        super.onResume()
        mainActivity.setStatusBarColor(R.color.main_bg)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity.growthNoteViewModel.growthList.observeNonNull(this) {
            if(it.isNotEmpty()){
                binding.emptyView.isVisible = false
                binding.rlCardView.isVisible =true
                mAdapter.setData(it)
            }
        }

    }


    fun setCardView(binding: GrowthDiaryBinding, dataList: ArrayList<ImprovementDiaries>) {

        if (dataList.isNullOrEmpty()) {
            binding.rlCardView.visibility = View.GONE
            binding.emptyView.visibility = View.VISIBLE
        } else {
            context?.let {
                binding.rlCardView.addItemDecoration(CardViewItemDecoration(it, dataList.size))

            }
            mAdapter.setData(dataList)
            binding.rlCardView.visibility = View.VISIBLE
            binding.emptyView.visibility = View.GONE
        }

    }

    override fun clickMonth(year: String, month: String) {
        mainActivity.getGrowthList(year, month)
    }
}