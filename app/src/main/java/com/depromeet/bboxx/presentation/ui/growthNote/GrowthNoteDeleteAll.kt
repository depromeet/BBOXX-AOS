package com.depromeet.bboxx.presentation.ui.growthNote

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.depromeet.bboxx.databinding.LayoutDeleteAllBinding
import com.depromeet.bboxx.databinding.LayoutToBackBinding
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class GrowthNoteDeleteAll(val emotionId: Int) : BottomSheetDialogFragment() {

    lateinit var mainActivity: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = LayoutDeleteAllBinding.inflate(inflater, container, false)


        initView(binding)
        return binding.root
    }


    private fun initView(binding: LayoutDeleteAllBinding) {



        binding.clBack.setOnClickListener {
            mainActivity.clearThisFragment(this)
        }

        binding.btnDeleteAll.setOnClickListener {
            deleteFeeling()
            this.dismiss()
            mainActivity.addFragment(GrowthNoteDeleteCompletelyFragment())
        }
        binding.btnCancle.setOnClickListener {
            this.dismiss()
        }

    }

    private fun deleteFeeling(){
        // Test EmotionId 해당 ID는 감정일기를 쓰면 1씩 증가되니깐 실제적으로 값을 받아오는지 보실려면
        //  감정일기 쓰고 값을 1 증가 시켜서 서버로 부터 데이터 받아오는지 봐주시면 될것 같습니다.
        val emotionId = 0
        mainActivity.feelingNoteViewModel.deleteFeelings(emotionId)
    }


}