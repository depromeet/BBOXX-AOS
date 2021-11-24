package com.depromeet.bboxx.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.ActivityMainBinding
import com.depromeet.bboxx.presentation.base.BaseActivity
import com.depromeet.bboxx.presentation.extension.extraNotNull
import com.depromeet.bboxx.presentation.viewmodel.*
import com.depromeet.bboxx.util.SharedPreferenceUtil
import com.depromeet.bboxx.util.constants.SharedConstants
import com.depromeet.bboxx.util.constants.SharedConstants.C_MEMBER_ID_SHRED
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    val mainViewModel: MainViewModel by viewModels()
    val decibelViewModel: DecibelViewModel by viewModels()
    val feelHistoryViewModel: FeelHistoryViewModel by viewModels()
    val feelingNoteViewModel: FeelingNoteViewModel by viewModels()
    val growthNoteViewModel: GrowthNoteViewModel by viewModels()

    private lateinit var viewPager: ViewPager2
    private var fragmentlist = mutableListOf<Fragment>()

    companion object {
        val EXTRA_FCM_DATA = "extra_fcm_data"
        val EXTRA_POSION = "extra_position"
    }
    val fcmData by extraNotNull(EXTRA_FCM_DATA, "")
    val position by extraNotNull(EXTRA_POSION, 0)

    var fcmTitle = ""
    var positionPager = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

        setAdapter(position)
    }

    private fun init() {
        with(binding) {
            lifecycleOwner = this@MainActivity
            vm = mainViewModel
        }
        fcmTitle = fcmData
        positionPager = position
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
     *  성장일기 리스트 보기
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

    /**
     *  감정일기 삭제
     */
    fun deleteFeelData(emotionId: Int) {
        feelingNoteViewModel.deleteFeelings(emotionId)
    }

    /**
     *  감정일기 조회
     */
    fun searchFeelingContent(emotionDiaryId: Int) {
        feelingNoteViewModel.searchFeelings(emotionDiaryId)
    }

    /**
     *  뷰페이저 Adpater
     */
    private fun setAdapter(position :Int) {
        viewPager = binding.vpMain
        viewPager.adapter = MainViewAdapter(this).apply {
            createFragment(position)
        }
    }

    fun addFragment(fragment: Fragment) {
        fragmentlist.add(fragment)
        supportFragmentManager.beginTransaction().add(R.id.fl_main, fragment).commit()
    }

    fun addTopFragment(fragment: Fragment) {
        fragmentlist.add(fragment)
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.anim_up, R.anim.anim_down)
        supportFragmentManager.beginTransaction().add(R.id.fl_main, fragment).commit()
    }

    fun clearThisFragment(fragment: Fragment) {
        fragmentlist.forEach {
            if(it == fragment){
                fragmentlist.remove(it)
            }
        }
        supportFragmentManager.beginTransaction().remove(fragment).commit()
    }

    fun allClearFragment(){
        fragmentlist.forEach {
            supportFragmentManager.beginTransaction().remove(it).commit()
        }
    }

    fun allClearAndGrowth(){
        fragmentlist.forEach {
            supportFragmentManager.beginTransaction().remove(it).commit()
        }
        viewPager.currentItem = viewPager.currentItem + 1
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }
}