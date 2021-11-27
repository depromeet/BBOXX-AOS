package com.depromeet.bboxx.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.ActivityMainBinding
import com.depromeet.bboxx.presentation.base.BaseActivity
import com.depromeet.bboxx.presentation.extension.extraNotNull
import com.depromeet.bboxx.presentation.extension.onMainThread
import com.depromeet.bboxx.presentation.viewmodel.*
import com.depromeet.bboxx.util.SharedPreferenceUtil
import com.depromeet.bboxx.util.constants.SharedConstants
import com.depromeet.bboxx.util.constants.SharedConstants.C_MEMBER_ID_SHRED
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.subjects.BehaviorSubject


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    val mainViewModel: MainViewModel by viewModels()
    val decibelViewModel: DecibelViewModel by viewModels()
    val feelHistoryViewModel: FeelHistoryViewModel by viewModels()
    val feelingNoteViewModel: FeelingNoteViewModel by viewModels()
    val growthNoteViewModel: GrowthNoteViewModel by viewModels()

    private lateinit var viewPager: ViewPager2
    private var fragmentlist = mutableListOf<Fragment>()
    private val backButtonSubject = BehaviorSubject.createDefault(0.toLong()).toSerialized()

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

        initBackButton()
        
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
        if(fragmentlist.size >= 1){
            supportFragmentManager.beginTransaction().remove(fragmentlist.last()).commit()
            fragmentlist.removeAt(fragmentlist.lastIndex)
        }
        else if(fragmentlist.size == 0){
            if (viewPager.currentItem == 0) {
                backButtonSubject.onNext(System.currentTimeMillis())
            } else {
                viewPager.currentItem = viewPager.currentItem - 1
            }
        }
    }

    private fun initBackButton() {
        disposable +=
            backButtonSubject
                .toFlowable(BackpressureStrategy.BUFFER)
                .onMainThread()
                .buffer(2, 1)
                .map { it[0] to it[1] }
                .subscribe(
                    {
                        if (it.second - it.first < 2000) {
                            finish()
                        } else {
                            exitAppToast()
                        }
                    }, {})
    }

    private fun exitAppToast(){
        Toast.makeText(this, "뒤로 버튼을 한번 더 누르시면 종료됩니다", Toast.LENGTH_SHORT).show()
    }

}