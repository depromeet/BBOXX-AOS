package com.depromeet.bboxx.presentation.ui.decibel

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.DecibelLayoutBinding
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.utils.CustomTopView
import com.depromeet.bboxx.util.AudioReaderJava


class DecibelFragment : Fragment() {
    var maxDecibel = 0

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

        val binding = DecibelLayoutBinding.inflate(inflater, container, false)

        initView(binding)
        return binding.root
    }


    @SuppressLint("ResourceType")
    fun initView(binding: DecibelLayoutBinding) {

        val audioReaderJava = AudioReaderJava()

        mainActivity.setStatusBarColor(R.color.black_80)

        binding.clTopView.setBackBtn(object : CustomTopView.OnclickCallback{
            override fun callback() {
                mainActivity.clearThisFragment(this@DecibelFragment)
            }

        }, resources.getString(R.color.main_bg) )

        binding.ivMic.setOnClickListener {

            object : CountDownTimer(1000 * 3 + 500, 1000) {
                @SuppressLint("SetTextI18n")
                override fun onTick(p0: Long) {
                    startDecibel(audioReaderJava)

                    when {
                        p0 / 1000 >= 3 -> {
                            binding.ivMic.background = ContextCompat.getDrawable(mainActivity, R.drawable.decibel_3)
                        }
                        p0 / 1000 >= 2 -> {
                            binding.ivMic.background = ContextCompat.getDrawable(mainActivity, R.drawable.decibel_2)
                            binding.tvDecibelInfo.text = "더 크게 네 ️감정을\n마음껏 소리쳐봐!"

                        }
                        p0 / 1000 >= 1 -> {
                            binding.ivMic.background = ContextCompat.getDrawable(mainActivity, R.drawable.decibel_1)
                            binding.tvDecibelInfo.text = "마지막까지 네 안에 있는\n모든 감정을 털어놓아봐!"
                        }
                        else -> {
                            binding.ivMic.background = ContextCompat.getDrawable(mainActivity, R.drawable.decibel_mic)
                        }
                    }

                }

                override fun onFinish() {

                    binding.ivMic.visibility = View.VISIBLE
                    audioReaderJava.stopReader()

                    mainActivity.addFragment(DecibelResultFragment(maxDecibel))
                    maxDecibel = 0
                }
            }.start()
        }
    }

    fun startDecibel(audioReader: AudioReaderJava) {
        val sampleRate = 8000
        val inputBlockSize = 256
        val sampleDecimate = 1
        audioReader.startReader(
            sampleRate,
            inputBlockSize * sampleDecimate,
            object : AudioReaderJava.Listener() {
                override fun onReadComplete(dB: Int) {
                    if (dB >= maxDecibel) maxDecibel = dB
                    Log.d("HAE", dB.toString())
                }

                override fun onReadError(error: Int) {}
            })
    }

    override fun onStop() {
        super.onStop()
        mainActivity.setStatusBarColor(R.color.select_bg)
    }
}