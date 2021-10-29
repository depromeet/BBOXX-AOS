package com.depromeet.bboxx.presentation.ui.growthNote

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.bboxx.R
import com.depromeet.bboxx.data.entity.ImprovementDiariesEntity
import com.depromeet.bboxx.data.entity.ImprovementTagsEntity
import com.depromeet.bboxx.databinding.EmotionDiaryEditLayoutBinding
import com.depromeet.bboxx.databinding.GrowthDiaryBinding
import com.depromeet.bboxx.databinding.GrowthFeelingNoteLayoutBinding
import com.depromeet.bboxx.presentation.ui.BackLayerFragment
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.ui.feelnote.FeelingNoteResultFragment
import com.depromeet.bboxx.presentation.utils.CardViewItemDecoration
import com.depromeet.bboxx.presentation.utils.CustomTopView

class GrowthNoteReViewFeelingNote() : Fragment() {

    lateinit var mainActivity: MainActivity

    val mAdapter = CardViewAdapter()

    var a = 0

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

        val binding = GrowthFeelingNoteLayoutBinding.inflate(inflater, container, false)




        binding.clTopView.setBackBtn(object :CustomTopView.OnclickCallback{
            override fun callback() {
                mainActivity.addTopFragment(BackLayerFragment(this@GrowthNoteReViewFeelingNote))
            }
        })



        return binding.root
    }


}