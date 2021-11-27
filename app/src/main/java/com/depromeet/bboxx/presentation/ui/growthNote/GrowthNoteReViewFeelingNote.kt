package com.depromeet.bboxx.presentation.ui.growthNote

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.depromeet.bboxx.databinding.GrowthFeelingNoteLayoutBinding
import com.depromeet.bboxx.presentation.extension.observeNonNull
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.ui.result.Result
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

        val emotionDiaryId = emotionId

        mainActivity.searchFeelingContent(emotionDiaryId)

        binding.btnSuccess.setOnClickListener {
            mainActivity.addTopFragment(GrwothNoteTagFragment(emotionDiaryId))
        }

        binding.btnDeleteAll.setOnClickListener {

            val bottomNote = GrowthNoteDeleteAll(emotionId) // emotionId 를 해당 클래스에 또 넘겨주셔야 합니다.
            bottomNote.show(childFragmentManager, bottomNote.tag)
        }

        mainActivity.feelingNoteViewModel.emotionDiary.observeNonNull(this){
            binding.tvDate.text = it.createdAt
            binding.etTitleText.text = it.title
            binding.etMainText.text = it.content
        }

        mainActivity.feelingNoteViewModel.searchNetworkErrorEvent.observeNonNull(this){
            when(it){
                is Result.Error ->{
                    errorEventMsg(it.exception)
                }
            }
        }

        return binding.root
    }

    private fun errorEventMsg(error: Throwable){
        val errorMsg = error.message
        Toast.makeText(requireContext(), "Error Msg: $errorMsg", Toast.LENGTH_SHORT).show()
    }

}