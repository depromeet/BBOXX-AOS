package com.depromeet.bboxx.presentation.ui.growthNote

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
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

        val binding = GrowthDiaryBinding.inflate(inflater, container, false)

        binding.clTopView.setBackBtn(object : CustomTopView.OnclickCallback {
            override fun callback() {
                mainActivity.clearThisFragment(this@GrowthNoteFragment)
            }
        }, resources.getString(R.color.white))

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

        getGrowthList(nowYear, nowMonth)

        binding.tvMonth.setOnClickListener {

            val monthList = arrayListOf<String>(nowMonth)
            nowYear = DateFormatter().formatNowYear()

            GrowthCalendarFragment.newInstance(this, monthList, nowYear)
                .show(childFragmentManager, GrowthCalendarFragment.TAG)

        }
//        val dataList = ArrayList<ImprovementDiariesEntity>()
//        val tag = ArrayList<ImprovementTagsEntity>()
//        dataList.add(
//            ImprovementDiariesEntity(
//                "지난 두 달간 써온 회고일기를 오늘 다시 읽어보았다. 얼핏 일기와 비슷해보이긴 하지만 조금 더 객관적이고 디테일하다는 측면에서 확연히 다르다. 나의 복잡다단한 감정을 쏟아내는 내면일기가 아니라, 명확한 질문을 두고 ‘나’ 라는 청자에게 쓰는 외면일기에 가깝다. 내가 무엇을 잘했고 부족했는지, 다음 단계로 나가기 위해서는 어떻게 해야하는지. 몇 주간의 기록을 쭉 훑어보니, 내가 일과 삶에 어떤 방향성을 지니고 싶어하는지가 보이고, 그러기 위해 노력하는 모습도 보여서 뿌듯했다.",
//                "",
//                1,
//                1,
//                1,
//                tag,
//                "타이틀1",
//                ""
//            )
//        )
//        dataList.add(ImprovementDiariesEntity("", "", 1, 1, 1, tag, "타이틀2", ""))
//        dataList.add(ImprovementDiariesEntity("", "", 1, 1, 1, tag, "타이틀3", ""))
//        dataList.add(ImprovementDiariesEntity("", "", 1, 1, 1, tag, "타이틀4", ""))
//        dataList.add(ImprovementDiariesEntity("", "", 1, 1, 1, tag, "타이틀5", ""))

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

            mainActivity.growthNoteViewModel.testSendNotification(2, memberId!!)
        }

        mainActivity.growthNoteViewModel.growthList.observeNonNull(this) {

        }
        return binding.root

    }

    /**
     *  성장일기 오늘 기준으로 성장일기 가져오는 함수입니다.
     */
    @SuppressLint("NewApi")
    private fun getGrowthList(yearNow: String, monthNow: String) {
        initSharedPreference(requireContext(), C_MEMBER_ID_SHRED)
        val memberId = getDataIntSharedPreference(C_MEMBER_ID_KEY)

        mainActivity.growthNoteViewModel.getGrowthList(
            memberId!!,
            monthNow.toInt(),
            yearNow.toInt()
        )
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

    override fun clickMonth(month: String) {
        getGrowthList(nowYear, month)
    }
}