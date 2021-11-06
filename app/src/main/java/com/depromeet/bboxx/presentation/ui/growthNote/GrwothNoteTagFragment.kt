package com.depromeet.bboxx.presentation.ui.growthNote

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.FragmentGrowthTagBinding
import com.depromeet.bboxx.presentation.base.BaseFragment
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.utils.CustomTopView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import org.jetbrains.anko.runOnUiThread
@RequiresApi(Build.VERSION_CODES.M)
class GrwothNoteTagFragment(private val emotionDiaryId: Int) : BaseFragment<FragmentGrowthTagBinding>(R.layout.fragment_growth_tag) {

    lateinit var mainActivity: MainActivity
    var chipGroup: ChipGroup? = null
    var tagList = mutableListOf<String>()

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


        val growthList = resources.getStringArray(R.array.feel_tag)
        chipGroup = binding.chipGroup
        growthList.forEach {
            val chip = Chip(requireContext())
            chip.text = it
            chip.textSize = 16F
            chipGroup!!.addView(chip)
            chip.setChipBackgroundColorResource(R.color.white)

            chip.setOnClickListener {
                if(tagList.size == 0)
                    chipActivation(chip)
                else{
                    tagList.forEach {  tag ->
                        if(tag == chip.text.toString()){
                            chipDisabled(chip)
                        }
                        else{
                            chipActivation(chip)
                        }
                    }
                }
            }
        }

        binding.btnGrowthTag.setOnClickListener {
            // Next Fragment 이동 하여 태그 가져가면 됩니다.
            mainActivity.addFragment(GrowthNoteWriteFragment(tagList, emotionDiaryId))
            // listData 가 태그 들어가 있습니다.
        }
    }

    private fun chipActivation(chip: Chip){
        chip.setTextColor(Color.WHITE)
        chip.setChipBackgroundColorResource(R.color.mypage_bg)

        tagList.add(chip.text.toString())

        if(tagList.size <= 5){
            activeBtn()
        }
    }

    private fun chipDisabled(chip: Chip){
        chip.setTextColor(Color.BLACK)
        chip.setChipBackgroundColorResource(R.color.white)

        tagList.remove(chip.text.toString())

        if(tagList.size == 0){
            disabledBtn()
        }
    }

    private fun activeBtn(){
        binding.btnGrowthTag.isClickable = true

        requireContext().runOnUiThread {
            binding.btnGrowthTag.setBackgroundResource(R.drawable.bg_btn_round_ten)
            binding.btnGrowthTag.setTextColor(Color.WHITE)
        }
    }


    private fun disabledBtn(){
        binding.btnGrowthTag.isClickable = false

        requireContext().runOnUiThread {
            binding.btnGrowthTag.setBackgroundResource(R.drawable.bg_btn_round_gray_ten)
            binding.btnGrowthTag.setTextColor(getColor(R.color.mypage_bg))
        }
    }
}