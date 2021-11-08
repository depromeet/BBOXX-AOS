package com.depromeet.bboxx.presentation.ui.feelnote

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.depromeet.bboxx.databinding.FeelingNoteSelectReasonLayoutBinding
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.utils.CustomTopView

class FeelingNoteSelectFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    var selectedFeeling: String = ""
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    private var categoryId: Int = -1

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FeelingNoteSelectReasonLayoutBinding.inflate(inflater, container, false)

        if (container != null) {
            initView(binding, container.context)
        }

        return binding.root
    }


    fun initView(binding: FeelingNoteSelectReasonLayoutBinding, ctx: Context) {
        var isActivated = false

        binding.clTopView.setBackBtn(object :CustomTopView.OnclickCallback{
            override fun callback() {
                mainActivity.clearThisFragment(this@FeelingNoteSelectFragment)
            }

        })

        binding.spFeeling.setOnClickListener {
            val bottomNote = FeelingNoteSelectBottomFragment ({
                binding.spFeeling.text = it
                selectedFeeling = it
                isActivated = true
                setBtn(binding.btnSuccess)
            },{
                categoryId = it+1
            })
            bottomNote.show(childFragmentManager, bottomNote.tag)
        }

        binding.btnSuccess.setOnClickListener {
            if (isActivated) {
                mainActivity.addFragment(FeelingNoteFragment(categoryId, selectedFeeling))
            }
        }

    }

    fun setBtn(bt: TextView) {
        bt.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#2C2C2C"))
        bt.setTextColor(Color.parseColor("#FFFFFF"))
    }
}

