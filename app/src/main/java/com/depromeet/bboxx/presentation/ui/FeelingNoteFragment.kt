package com.depromeet.bboxx.presentation.ui

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
import com.depromeet.bboxx.databinding.EmotionDiaryEditLayoutBinding

class FeelingNoteFragment(val selectedFeeling: String) : Fragment() {

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    var isButtonActivated = false

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var isTitleActivated = false
        var isMainActivated = false
        val binding = EmotionDiaryEditLayoutBinding.inflate(inflater, container, false)

        binding.etTitleText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {

                if (isTitleActivated != binding.etTitleText.text.isNotEmpty()) {
                    isTitleActivated = binding.etTitleText.text.isNotEmpty()
                    isActivatedButton(binding, isTitleActivated, isMainActivated)

                }

            }
        })

        binding.btnBack.setOnClickListener {
            mainActivity.addTopFragment(BackLayerFragment(this))
        }

        binding.etMainText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                isTitleActivated = binding.etMainText.text.isNotEmpty()
                val input: String = binding.etMainText.getText().toString()
                binding.tvTextCount.text = input.length.toString()
                when {
                    input.isEmpty() -> {
                        isMainActivated = false
                        isActivatedButton(binding, isTitleActivated, isMainActivated)
                    }
                    input.length < 1200 -> {
                        isMainActivated = true
                        binding.tvTextCount.setTextColor(Color.parseColor("#222222"))

                        isActivatedButton(binding, isTitleActivated, isMainActivated)
                    }
                    else -> {
                        isMainActivated = false
                        binding.tvTextCount.setTextColor(Color.parseColor("#FF4444"))

                        isActivatedButton(binding, isTitleActivated, isMainActivated)
                    }
                }

            }
        })
        binding.btnSuccess.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FF4444"))




        binding.btnSuccess.setOnClickListener {
            //TODO HAERIN 감정 선택으로 이동
            //selectedFeeling, 글쓴 내용 같이 이동
        }
        return binding.root
    }


    fun isActivatedButton(
        binding: EmotionDiaryEditLayoutBinding,
        isTitle: Boolean,
        isMain: Boolean
    ) {
        if (isTitle && isMain) {
            binding.btnSuccess.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor("#FF4444"))
            isButtonActivated = true

        } else {
            binding.btnSuccess.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor("#332C2C2C"))
            isButtonActivated = false
        }
    }
}