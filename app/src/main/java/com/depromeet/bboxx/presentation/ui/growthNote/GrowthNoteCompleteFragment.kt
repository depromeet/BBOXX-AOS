package com.depromeet.bboxx.presentation.ui.growthNote

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.GrowthNoteCompleteLayoutBinding
import com.depromeet.bboxx.presentation.model.GrowthNoteModel
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.utils.CustomTopView

class GrowthNoteCompleteFragment(private val growthNoteModelData: GrowthNoteModel) : Fragment() {

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    private lateinit var growthNoteModel: GrowthNoteModel

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = GrowthNoteCompleteLayoutBinding.inflate(inflater, container, false)

        binding.clTopView.setRightBtn(object : CustomTopView.OnclickCallback {
            override fun callback() {
                mainActivity.allClearFragment()
            }
        }, R.drawable.ic_close)

        modelDataInit()

        writeGrowth()

        binding.btGoToCardView.setOnClickListener {
            mainActivity.allClearAndGrowth()
        }

        return binding.root
    }

    private fun modelDataInit() {
        growthNoteModel = growthNoteModelData
    }

    //  성장일기 쓰기 API call
    private fun writeGrowth() {
        if (::growthNoteModel.isInitialized) {
            mainActivity.growthNoteViewModel.writeGrowth(
                growthNoteModel.content,
                growthNoteModel.emotionDiaryId,
                growthNoteModel.tags,
                growthNoteModel.title
            )
        }
    }


}