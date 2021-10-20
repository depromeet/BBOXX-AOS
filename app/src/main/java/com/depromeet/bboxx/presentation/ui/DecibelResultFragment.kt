package com.depromeet.bboxx.presentation.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import com.depromeet.bboxx.databinding.DecibelResultLayoutBinding


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


    private fun initView(binding: DecibelResultLayoutBinding) {
        binding.tvDecibel.text = dB.toString() + "dB"
        //TODO HAERIN 감정 측정도에 맞게 세팅
        binding.ivDecibelGauge.updateLayoutParams {

            val sampleDp = 414
            val density = resources.displayMetrics.density
            val value = (sampleDp * density).toInt()

            height = (value * 0.5).toInt()

        }


    }

}