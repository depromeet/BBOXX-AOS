package com.depromeet.bboxx.presentation.ui.growthNote

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.depromeet.bboxx.databinding.GrowthFeelingNoteLayoutBinding
import com.depromeet.bboxx.presentation.extension.observeNonNull
import com.depromeet.bboxx.presentation.ui.BackLayerFragment
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.utils.CustomTopView

class GrowthNoteReViewFeelingNote(val emotionId: Int): Fragment() {

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }


    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = GrowthFeelingNoteLayoutBinding.inflate(inflater, container, false)

        binding.clTopView.setBackBtn(object :CustomTopView.OnclickCallback{
            override fun callback() {
                mainActivity.clearThisFragment(this@GrowthNoteReViewFeelingNote)
            }
        })

        val emotionDiaryId = 0
        binding.btnSuccess.setOnClickListener {
            mainActivity.addTopFragment(GrwothNoteTagFragment(emotionDiaryId))
        }

        binding.btnDeleteAll.setOnClickListener {

            val bottomNote = GrowthNoteDeleteAll(emotionId) // emotionId 를 해당 클래스에 또 넘겨주셔야 합니다.
            bottomNote.show(childFragmentManager, bottomNote.tag)
        }

        mainActivity.feelingNoteViewModel.emotionDiary.observeNonNull(this){
            //  여기에서 감정일기 요청한 데이터 화면에 뿌려주시는 작업하시면 될것 같습니다.
        }

        return binding.root
    }

    fun getFeeling(){
        // Test EmotionId 해당 ID는 감정일기를 쓰면 1씩 증가되니깐 실제적으로 값을 받아오는지 보실려면
        //  감정일기 쓰고 값을 1 증가 시켜서 서버로 부터 데이터 받아오는지 봐주시면 될것 같습니다.
        val emotionId: Int = 0
        mainActivity.feelingNoteViewModel.searchFeelings(emotionId)
    }

}