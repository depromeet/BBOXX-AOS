package com.depromeet.bboxx.presentation.ui.growthNote

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.FragmentGrowthTagBinding
import com.depromeet.bboxx.presentation.base.BaseFragment
import com.depromeet.bboxx.presentation.model.TimelineGrowthTagModel
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.utils.CustomTopView
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import org.jetbrains.anko.runOnUiThread
@RequiresApi(Build.VERSION_CODES.M)
class GrwothNoteTagFragment(private val emotionDiaryId: Int) : BaseFragment<FragmentGrowthTagBinding>(R.layout.fragment_growth_tag) {

    lateinit var mainActivity: MainActivity
    var tagList = mutableListOf<String>()
    private val growthTagList = mutableListOf<TimelineGrowthTagModel>()

    private val growthNoteTagAdapter: GrowthNoteTagAdapter by lazy{
        GrowthNoteTagAdapter({list ->
            tagList.clear()
            tagList.addAll(list)

            if(tagList.size in 1..5){
                activeBtn()
            }
            else {
                disabledBtn()
            }
        }, {status ->

            if(status){
                onToast("최대 다섯개까지 선택 가능합니다")
            }
        })
    }

    private fun onToast(msg: String){
        Toast.makeText(requireContext(), msg ,Toast.LENGTH_SHORT).show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingInit()

        setAdapterInit()

        setAdapterListData()

        binding.clTopView.setBackBtn(object : CustomTopView.OnclickCallback{
            override fun callback() {
                mainActivity.clearThisFragment(this@GrwothNoteTagFragment)
            }
        }, resources.getString(R.color.mypage_bg) )

        binding.btnGrowthTag.setOnClickListener {
            // Next Fragment 이동 하여 태그 가져가면 됩니다.
            if(tagList.size == 0){
                onToast("감정을 선택해 주세요.")
            }
            else{
                mainActivity.addFragment(GrowthNoteWriteFragment(tagList, emotionDiaryId))
            }
        }
    }

    private fun bindingInit(){
        binding.lifecycleOwner = this
        binding.btnGrowthTag.isClickable = false
    }

    private fun setAdapterInit(){
        growthNoteTagAdapter.notifyDataSetChanged()

        val flexboxLayoutManager = FlexboxLayoutManager(requireContext()).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            alignItems = AlignItems.STRETCH
        }

        binding.ryGrowth.run{
            layoutManager = flexboxLayoutManager
            adapter = growthNoteTagAdapter
            setHasFixedSize(false)
        }
    }

    private fun setAdapterListData(){
        val growthList = resources.getStringArray(R.array.feel_tag)
        growthList.forEach {
            growthTagList.add(TimelineGrowthTagModel(it, false))
        }
        growthNoteTagAdapter.setGrowthTagList(growthTagList)
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