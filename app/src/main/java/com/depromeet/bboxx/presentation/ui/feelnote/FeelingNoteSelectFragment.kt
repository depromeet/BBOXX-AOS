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
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.FeelingNoteSelectReasonLayoutBinding
import com.depromeet.bboxx.presentation.ui.BackLayerFragment
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.utils.CustomTopView
import com.depromeet.bboxx.util.SharedPreferenceUtil
import com.depromeet.bboxx.util.constants.SharedConstants

class FeelingNoteSelectFragment(private val colorNumber: Int) : Fragment() {

    lateinit var mainActivity: MainActivity
    var selectedFeeling: String = ""
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    private var categoryId: Int = 0

    override fun onResume() {
        super.onResume()

        mainActivity.setStatusBarColor(R.color.main_bg)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity.setStatusBarColor(R.color.main_bg)

        val binding = FeelingNoteSelectReasonLayoutBinding.inflate(inflater, container, false)

        if (container != null) {
            initView(binding, container.context)
        }

        return binding.root
    }


    fun initView(binding: FeelingNoteSelectReasonLayoutBinding, ctx: Context) {
        var isActivated = false

        SharedPreferenceUtil.initSharedPreference(
            requireContext(),
            SharedConstants.C_EMOTION_ID_SHARED
        )
        val emotionId =
            SharedPreferenceUtil.getDataIntSharedPreference(SharedConstants.C_EMOTION_ID_KEY)
        categoryId = if(emotionId == 0){
            1
        } else{
            emotionId!! + 1
        }

        binding.clTopView.setBackBtn(object :CustomTopView.OnclickCallback{
            override fun callback() {
                //mainActivity.clearThisFragment(this@FeelingNoteSelectFragment)
                val bottomNote = BackLayerFragment(this@FeelingNoteSelectFragment)
                bottomNote.show(childFragmentManager, bottomNote.tag)
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

    private fun setBtn(bt: TextView) {
        bt.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#2C2C2C"))
        bt.setTextColor(Color.parseColor("#FFFFFF"))
    }

    override fun onStop() {
        super.onStop()
        onRestartViewTitleColorChange()
    }

    private fun onRestartViewTitleColorChange(){
        when(colorNumber){
            1 -> mainActivity.setStatusBarColor(R.color.color_6AA13D)
            2 -> mainActivity.setStatusBarColor(R.color.color_A8BD28)
            3 -> mainActivity.setStatusBarColor(R.color.color_EF9E24)
            4 -> mainActivity.setStatusBarColor(R.color.color_EF9E24)
            5 -> mainActivity.setStatusBarColor(R.color.color_EF9E24)
            6 -> mainActivity.setStatusBarColor(R.color.color_D04141)
            else -> mainActivity.setStatusBarColor(R.color.select_bg)
        }
    }

}

