package com.depromeet.bboxx.presentation.ui.onboard

import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.ActivityOnboardingBinding
import com.depromeet.bboxx.presentation.base.BaseActivity
import com.depromeet.bboxx.presentation.extension.observeNonNull
import com.depromeet.bboxx.presentation.ui.navigation.NavigatorUI
import com.depromeet.bboxx.presentation.ui.onboard.fragment.OnboardFragmentStateAdapter
import com.depromeet.bboxx.presentation.viewmodel.OnboardViewModel

class OnboardActivity : BaseActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {

    private val onboardViewModel: OnboardViewModel by viewModels()
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        setAdapter()

        onboardViewModel.onboardNextEvent.observeNonNull(this){
            NavigatorUI.toRefuse(this)
            finish()
        }
    }

    private fun initBinding() {
        with(binding) {
            lifecycleOwner = this@OnboardActivity
        }
    }

    private fun setAdapter(){
        viewPager = binding.vpOnboard
        viewPager.adapter = OnboardFragmentStateAdapter(this)
    }

    override fun onBackPressed() {
        if(viewPager.currentItem == 0) {
            super.onBackPressed()
        }else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }
}