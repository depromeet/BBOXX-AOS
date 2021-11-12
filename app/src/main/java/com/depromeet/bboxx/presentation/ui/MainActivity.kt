package com.depromeet.bboxx.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.ActivityMainBinding
import com.depromeet.bboxx.presentation.base.BaseActivity
import com.depromeet.bboxx.presentation.viewmodel.*
import com.depromeet.bboxx.util.SharedPreferenceUtil
import com.depromeet.bboxx.util.constants.SharedConstants
import com.depromeet.bboxx.util.constants.SharedConstants.C_MEMBER_ID_SHRED
import dagger.hilt.android.AndroidEntryPoint
import android.content.Intent




@AndroidEntryPoint
class MainActivity() : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    val mainViewModel: MainViewModel by viewModels()
    val decibelViewModel: DecibelViewModel by viewModels()
    val feelHistoryViewModel: FeelHistoryViewModel by viewModels()
    val feelingNoteViewModel: FeelingNoteViewModel by viewModels()
    val growthNoteViewModel: GrowthNoteViewModel by viewModels()

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

        setAdapter(intent.getIntExtra("position", 0))
    }

    private fun init() {
        with(binding) {
            lifecycleOwner = this@MainActivity
            vm = mainViewModel
        }
    }

    /**
     *  감정일기에 쓸 이모션 불러오기
     */
    fun getEmotionListImage() {
        //  감정일기 이모션 불러오기
        feelingNoteViewModel.getFeeling()
    }

    /**
     *  타임라인 보기
     */
    fun requestFeelHistoryList() {
        SharedPreferenceUtil.initSharedPreference(this, C_MEMBER_ID_SHRED)
        val memberId =
            SharedPreferenceUtil.getDataIntSharedPreference(SharedConstants.C_MEMBER_ID_KEY)

        feelHistoryViewModel.getNoticeList(memberId!!)
    }


    /**
     *  성장일기 리스트 보
     */
    fun getGrowthList(yearNow: String, monthNow: String) {
        SharedPreferenceUtil.initSharedPreference(this, C_MEMBER_ID_SHRED)
        val memberId =
            SharedPreferenceUtil.getDataIntSharedPreference(SharedConstants.C_MEMBER_ID_KEY)

        growthNoteViewModel.getGrowthList(
            memberId!!,
            monthNow.toInt(),
            yearNow.toInt()
        )
    }

    fun deleteFeelData(emotionId: Int) {
        feelingNoteViewModel.deleteFeelings(emotionId)
    }

    fun searchFeelingContent(emotionDiaryId: Int) {
        feelingNoteViewModel.searchFeelings(emotionDiaryId)
    }

    fun beforeFeelingContent(emotionDiaryId: Int) {
        feelingNoteViewModel.searchFeelings(emotionDiaryId)
    }

    private fun setAdapter(position :Int) {
        viewPager = binding.vpMain
        viewPager.adapter = MainViewAdapter(this).apply {
            createFragment(position)
        }

    }

    fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.fl_main, fragment).commit()
    }

    fun replaceFragment(replacefragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fl_main, replacefragment).commit(); }

    fun addTopFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.anim_up, R.anim.anim_down)
        supportFragmentManager.beginTransaction().add(R.id.fl_main, fragment).commit()
    }

    fun clearThisFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().remove(fragment).commit()
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }
}