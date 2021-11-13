package com.depromeet.bboxx.presentation.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.depromeet.bboxx.presentation.ui.growthNote.GrowthNoteFragment

class MainViewAdapter(fragmentActivity: FragmentActivity):
    FragmentStateAdapter(fragmentActivity){

    override fun getItemCount(): Int {
        return 2
    }
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> MainFragment()
            else -> GrowthNoteFragment()
        }
    }
}