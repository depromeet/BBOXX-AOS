package com.depromeet.bboxx.presentation.ui.growthNote

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.FragmentGrowthTagBinding
import com.depromeet.bboxx.presentation.base.BaseFragment
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.utils.CustomTopView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import org.jetbrains.anko.runOnUiThread

class GrwothNoteTagFragment : BaseFragment<FragmentGrowthTagBinding>(R.layout.fragment_growth_tag) {

    lateinit var mainActivity: MainActivity
    var chipGroup: ChipGroup? = null
    var listData = mutableListOf<String>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGrowthTag.isClickable = false

        binding.clTopView.setBackBtn(object : CustomTopView.OnclickCallback{
            override fun callback() {
                mainActivity.clearThisFragment(this@GrwothNoteTagFragment)
            }

        }, resources.getString(R.color.mypage_bg) )


        val dummyTexts = resources.getStringArray(R.array.feel_tag)
        chipGroup = binding.chipGroup
        dummyTexts.forEach {
            val chip = Chip(requireContext())
            chip.text = it
            chip.textSize = 16F
            chipGroup!!.addView(chip)

            chip.setOnClickListener {
                chip.setTextColor(Color.WHITE)
                chip.setChipBackgroundColorResource(R.color.mypage_bg)

                listData.add(chip.text.toString())

                if(listData.size == 5){
                    changeBtn()
                }
            }
        }

        binding.btnGrowthTag.setOnClickListener {
            // Next Fragment 이동 하여 태그 가져가면 됩니다.
            mainActivity.addFragment(GrowthNoteWriteFragment())
            // listData 가 태그 들어가 있습니다.
        }
    }

    private fun changeBtn(){
        binding.btnGrowthTag.isClickable = true

        requireContext().runOnUiThread {
            binding.btnGrowthTag.setBackgroundResource(R.drawable.bg_btn_round_ten)
            binding.btnGrowthTag.setTextColor(Color.WHITE)
        }
    }
}