package com.depromeet.bboxx.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.ActivityMainBinding
import com.depromeet.bboxx.presentation.base.BaseActivity
import com.depromeet.bboxx.presentation.extension.observeNonNull
import com.depromeet.bboxx.presentation.model.NotificationModel
import com.depromeet.bboxx.presentation.viewmodel.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    val mainViewModel: MainViewModel by viewModels()
    val decibelViewModel: DecibelViewModel by viewModels()
    val feelHistoryViewModel: FeelHistoryViewModel by viewModels()
    val feelingNoteViewModel: FeelingNoteViewModel by viewModels()
    val growthNoteViewModel: GrowthNoteViewModel by viewModels()

    private lateinit var viewPager: ViewPager2

    private lateinit var noticeModel: List<NotificationModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        init()
        setAdapter()

        feelHistoryViewModel.noticeList.observeNonNull(this){
            noticeModel = it
        }
    }

    private fun init() {
        with(binding) {
            lifecycleOwner = this@MainActivity
            vm = mainViewModel
        }
    }

    fun getEmotionListImage(){
        //  감정일기 이모션 불러오기
        feelingNoteViewModel.getFeeling()
    }

    private fun setAdapter() {
        viewPager = binding.vpMain
        viewPager.adapter = MainViewAdapter(this)
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