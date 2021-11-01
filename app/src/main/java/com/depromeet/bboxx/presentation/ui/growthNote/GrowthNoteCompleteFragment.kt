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
import com.depromeet.bboxx.presentation.ui.MainFragment
import com.depromeet.bboxx.presentation.utils.CustomTopView

class GrowthNoteCompleteFragment(private val growthNoteModel : GrowthNoteModel) : Fragment() {

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

        val binding = GrowthNoteCompleteLayoutBinding.inflate(inflater, container, false)

        binding.clTopView.setRightBtn(object  : CustomTopView.OnclickCallback{
            override fun callback() {
                mainActivity.replaceFragment(MainFragment())
            }
        }, R.drawable.ic_close)

        binding.btGoToCardView.setOnClickListener {
            mainActivity.addFragment(GrowthNoteFragment())
        }
        return binding.root
    }



}