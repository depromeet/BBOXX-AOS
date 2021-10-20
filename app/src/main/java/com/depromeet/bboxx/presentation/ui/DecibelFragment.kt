package com.depromeet.bboxx.presentation.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.depromeet.bboxx.databinding.DecibelLayoutBinding
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


    fun initView(binding: DecibelLayoutBinding) {

        val audioReaderJava = AudioReaderJava()

        binding.progressCircular.setOnClickListener {
            binding.textCount.visibility = View.VISIBLE
            binding.ivMic.visibility = View.GONE

            object : CountDownTimer(1000 * 3 + 500, 1000) {
                @SuppressLint("SetTextI18n")
                override fun onTick(p0: Long) {
                    startDecibel(audioReaderJava)

                    binding.textCount.text = (p0 / 1000).toString()
                    when {
                        p0 / 1000 >= 3 -> {
                            binding.progressCircular.progress = 30
                        }
                        p0 / 1000 >= 2 -> {
                            binding.progressCircular.progress = 60
                            binding.tvDecibelInfo.text = "더 크게 네 ️감정을\n마음껏 소리쳐봐!"

                        }
                        p0 / 1000 >= 1 -> {
                            binding.progressCircular.progress = 90
                            binding.tvDecibelInfo.text = "마지막까지 네 안에 있는\n모든 감정을 털어놓아봐!"
                        }
                        else -> {
                            binding.progressCircular.progress = 120
                        }
                    }

                }

                override fun onFinish() {

                    binding.progressCircular.progress = 0
                    binding.ivMic.visibility = View.VISIBLE
                    binding.textCount.visibility = View.GONE
                    audioReaderJava.stopReader()

                    mainActivity.addFragment(DecibelResultFragment(maxDecibel))
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
                }

                override fun onReadError(error: Int) {}
            })
    }
}