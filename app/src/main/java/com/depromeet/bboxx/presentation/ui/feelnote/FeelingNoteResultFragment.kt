package com.depromeet.bboxx.presentation.ui.feelnote

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.bboxx.databinding.EmotionDiaryResultLayoutBinding
import com.depromeet.bboxx.presentation.model.SelectFeelingEmotionModel
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.util.SharedPreferenceUtil.getDataIntSharedPreference
import com.depromeet.bboxx.util.SharedPreferenceUtil.initSharedPreference
import com.depromeet.bboxx.util.SharedPreferenceUtil.setDataIntSharedPreference
import com.depromeet.bboxx.util.constants.SharedConstants

class FeelingNoteResultFragment(val categoryId: Int, val selectedFeeling: String, val feelingList : MutableList<SelectFeelingEmotionModel>, val title : String, val main : String ) : Fragment() {

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = EmotionDiaryResultLayoutBinding.inflate(inflater, container, false)

        binding.rlFeeling.adapter = FeelingSelectedAdapter(mainActivity).apply {
            setData(feelingList)
        }

        val layoutManager = LinearLayoutManager(mainActivity)
        layoutManager.orientation = RecyclerView.HORIZONTAL
        binding.rlFeeling.layoutManager = layoutManager
        val string = SpannableStringBuilder("오늘 "+selectedFeeling+"로 힘들었어.")
        binding.tvMainTitle02.text = title
        binding.tvSubText.text = main

        string.apply{
            setSpan(StyleSpan(Typeface.BOLD), 3, 3+selectedFeeling.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        binding.btnSuccess.setOnClickListener {
            writeFeelingNoteData()
            
            mainActivity.addFragment(FeelingNoteCompleteFragment())
        }
      binding.tvMainTitle01.text =string
        return binding.root
    }

    /**
     *  감정일기 쓴 내용을 서버로 전달합니다.
     */
    private fun writeFeelingNoteData(){
        // emotionList는 FeelingEmotionModel id 값을 기준으로 선택한 감정 리스트를 전달해줍니다.
        val emotionIdList = mutableListOf<Int>()
        feelingList.forEach {
            emotionIdList.add(it.id)
        }

        var memberId = -1

        initSharedPreference(requireContext(), SharedConstants.C_MEMBER_ID_SHRED)
        memberId = getDataIntSharedPreference(SharedConstants.C_MEMBER_ID_KEY)!!

        mainActivity.feelingNoteViewModel.writeFeeling(categoryId, main, emotionIdList, memberId, title)

        testSendNotification(memberId)

    }

    /**
     *  Test Send Notification
     */
    private fun testSendNotification(memberId: Int){
        initSharedPreference(requireContext(), SharedConstants.C_EMOTION_ID_TEST_SHARED)
        var emotionId = getDataIntSharedPreference(SharedConstants.C_EMOTION_ID_TEST_KEY)
        if(emotionId == 0){
            setDataIntSharedPreference(1, SharedConstants.C_EMOTION_ID_TEST_KEY)
            mainActivity.growthNoteViewModel.testSendNotification(1, memberId)
        }
        else{
            emotionId = emotionId!! + 1
            setDataIntSharedPreference(emotionId, SharedConstants.C_EMOTION_ID_TEST_KEY)
            mainActivity.growthNoteViewModel.testSendNotification(emotionId, memberId)
        }
    }

}