package com.depromeet.bboxx.presentation.ui.decibel

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.DecibelResultLayoutBinding
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.ui.MainFragment
import com.depromeet.bboxx.presentation.ui.feelhistory.FeelingHistoryFragment
import com.depromeet.bboxx.presentation.ui.feelnote.FeelingNoteSelectFragment
import com.depromeet.bboxx.presentation.ui.navigation.NavigatorUI
import com.depromeet.bboxx.presentation.ui.navigation.NavigatorUI.toLogin
import com.depromeet.bboxx.presentation.ui.navigation.NavigatorUI.toMain
import com.depromeet.bboxx.presentation.ui.navigation.NavigatorUI.toNickName
import com.depromeet.bboxx.presentation.utils.CustomTopView


class DecibelResultFragment(val dB: Int) : Fragment() {

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

        val binding = DecibelResultLayoutBinding.inflate(inflater, container, false)


        initView(binding)
        return binding.root
    }


    @SuppressLint("ResourceType")
    private fun initView(binding: DecibelResultLayoutBinding) {

        binding.clTopView.setRightBtn(object  : CustomTopView.OnclickCallback{
            override fun callback() {
                mainActivity.finish()
                toMain(mainActivity)

            }
        }, R.drawable.ic_close, resources.getString(R.color.main_bg) )
        binding.tvResutDb.text = dB.toString() + "dB"
        //TODO HAERIN 감정 측정도에 맞게 세팅

        @SuppressLint("SetTextI18n")

        when(dB){
            in 0..50 -> {
                binding.clBg.setBackgroundColor(Color.parseColor(resources.getString(R.color.color_6AA13D)))
                binding.tvResultInfo.text = "내가 너의 말을 들어 줄 수 있는\n친구가 되어 줄게🍃"
                binding.imgLogo.background = mainActivity.getDrawable(R.drawable.decibel_0_50)
            }
            in 51..69 -> {
                binding.clBg.setBackgroundColor(Color.parseColor(resources.getString(R.color.color_A8BD28)))
                binding.tvResultInfo.text = "괜찮아 괜찮아 \n그럴 떄도 있는거야☁️"
                binding.imgLogo.background = mainActivity.getDrawable(R.drawable.decibel_51_69)

            }
            in 70..89 -> {

                binding.clBg.setBackgroundColor(Color.parseColor(resources.getString(R.color.color_EF9E24)))
                binding.tvResultInfo.text = "좀더 크게 감정을 표현하고 나면\n기분이 나아질꺼야💥"
                binding.imgLogo.background = mainActivity.getDrawable(R.drawable.decibel_70_89)

            }
            in 90..99 -> {
                binding.clBg.setBackgroundColor(Color.parseColor(resources.getString(R.color.color_EF9E24)))
                binding.tvResultInfo.text = "잘했어. 속에 있는 건 다 풀어야해.\n불족어때?🔥"
                binding.imgLogo.background = mainActivity.getDrawable(R.drawable.decibel_90_99)

            }
            in 100..119  -> {
            binding.clBg.setBackgroundColor(Color.parseColor(resources.getString(R.color.color_EF9E24)))
            binding.tvResultInfo.text = "와, 마음 속에 허리케인이\n몰아치고 갔었네🌪"
            }
            else -> {
                binding.clBg.setBackgroundColor(Color.parseColor(resources.getString(R.color.color_D04141)))
                binding.tvResultInfo.text = "운석이 충돌한 줄 알았어!\n속 시원하게 다 게웠어?☄️"
                binding.imgLogo.background = mainActivity.getDrawable(R.drawable.decibel_120)
            }

        }
        binding.btnAgain.setOnClickListener {
            mainActivity.clearThisFragment(this)
        }

        binding.btGoToFeelingNote.setOnClickListener {
            mainActivity.addFragment(FeelingNoteSelectFragment())
        }

    }

}