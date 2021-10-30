package com.depromeet.bboxx.presentation.ui.feelnote

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
import com.depromeet.bboxx.presentation.ui.BackLayerFragment
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.utils.CustomTopView

class FeelingNoteFragment(val selectedFeeling: String) : Fragment() {

    lateinit var mainActivity: MainActivity

    var isTitleActivated = false
    var isMainActivated = false

    var titleText = ""
    var mainTitle = ""
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

        val binding = EmotionDiaryEditLayoutBinding.inflate(inflater, container, false)

        binding.etTitleText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {

                titleText = binding.etTitleText.getText().toString()

                if (isTitleActivated != binding.etTitleText.text.isNotEmpty()) {
                    isTitleActivated = binding.etTitleText.text.isNotEmpty()
                    isActivatedButton(binding)

                }

            }
        })

        binding.clTopView.setBackBtn(object :CustomTopView.OnclickCallback{
            override fun callback() {
                mainActivity.addTopFragment(BackLayerFragment(this@FeelingNoteFragment))
            }
        })

        binding.clTopView.setRedoBtn(object :CustomTopView.OnclickCallback{
            override fun callback() {
                binding.etTitleText.text = null
                binding.etMainText.text = null
                isMainActivated = false
                isTitleActivated = false
                isActivatedButton(binding)
            }

        })

        binding.etMainText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                isTitleActivated = binding.etMainText.text.isNotEmpty()
                val input: String = binding.etMainText.getText().toString()
                binding.tvTextCount.text = input.length.toString()
                mainTitle = input
                when {
                    input.isEmpty() -> {
                        isMainActivated = false
                        isActivatedButton(binding)
                    }
                    input.length < 1200 -> {
                        isMainActivated = true
                        binding.tvTextCount.setTextColor(Color.parseColor("#222222"))

                        isActivatedButton(binding)
                    }
                    else -> {
                        isMainActivated = false
                        binding.tvTextCount.setTextColor(Color.parseColor("#FF4444"))

                        isActivatedButton(binding)
                    }
                }

            }
        })

        binding.btnSuccess.setOnClickListener {

            mainActivity.addFragment(FeelingNoteSelectFeelingFragment(selectedFeeling, titleText, mainTitle))


            //selectedFeeling, 글쓴 내용 같이 이동
        }
        return binding.root
    }


    fun isActivatedButton(
        binding: EmotionDiaryEditLayoutBinding
    ) {
        if (isTitleActivated && isMainActivated) {
            binding.btnSuccess.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor("#2C2C2C"))
            isButtonActivated = true

        } else {
            binding.btnSuccess.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor("#332C2C2C"))
            isButtonActivated = false
        }
    }
}