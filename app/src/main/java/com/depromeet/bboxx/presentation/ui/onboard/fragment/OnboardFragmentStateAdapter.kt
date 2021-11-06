package com.depromeet.bboxx.presentation.ui.onboard.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardFragmentStateAdapter(fragmentActivity: FragmentActivity):
    FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> OnboardOneFragment()
            1 -> OnboardTwoFragment()
            2 -> OnboardThreeFragment()
            3 -> OnboardFourFragment()
            else -> OnboardFiveFragment()
        }
    }
}