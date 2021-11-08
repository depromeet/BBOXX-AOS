package com.depromeet.bboxx.presentation.ui.growthNote

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.depromeet.bboxx.databinding.GrowthNoteEditLayoutBinding
import com.depromeet.bboxx.presentation.model.GrowthNoteModel
import com.depromeet.bboxx.presentation.model.GrowthNoteTagModel
import com.depromeet.bboxx.presentation.ui.BackLayerFragment
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.utils.CustomTopView
import com.depromeet.bboxx.util.DateFormatter
import com.depromeet.bboxx.util.SharedPreferenceUtil.getDataIntSharedPreference
import com.depromeet.bboxx.util.SharedPreferenceUtil.initSharedPreference
import com.depromeet.bboxx.util.constants.SharedConstants.C_MEMBER_ID_KEY
import com.depromeet.bboxx.util.constants.SharedConstants.C_MEMBER_ID_SHRED

class GrowthNoteWriteFragment(private val tagList: List<String>, private val emotionDiaryId: Int) :
    Fragment() {

    lateinit var mainActivity: MainActivity

    var isTitleActivated = false
    var isMainActivated = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    var isButtonActivated = false

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = GrowthNoteEditLayoutBinding.inflate(inflater, container, false)
        binding.tvTextDate.text = DateFormatter().growthNowTime()

        binding.etTitleText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {

                if (isTitleActivated != binding.etTitleText.text.isNotEmpty()) {
                    isTitleActivated = binding.etTitleText.text.isNotEmpty()
                    isActivatedButton(binding)

                }

            }
        })

        binding.clTopView.setBackBtn(object : CustomTopView.OnclickCallback {
            override fun callback() {

                val bottomNote = BackLayerFragment(this@GrowthNoteWriteFragment)
                bottomNote.show(childFragmentManager, bottomNote.tag)
            }
        })

        binding.clTopView.setRedoBtn(object : CustomTopView.OnclickCallback {
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
                val input: String = binding.etMainText.text.toString()
                binding.tvTextCount.text = input.length.toString()
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
            initSharedPreference(requireContext(), C_MEMBER_ID_SHRED)
            val memberId = getDataIntSharedPreference(C_MEMBER_ID_KEY)
            val tagListModel = arrayListOf<GrowthNoteTagModel>()
            tagList.forEach {
                tagListModel.add(GrowthNoteTagModel(it))
            }

            val growthNoteModel = GrowthNoteModel(
                binding.etMainText.text.toString(),
                emotionDiaryId,
                memberId!!,
                tagListModel,
                binding.etTitleText.text.toString()
            )
            mainActivity.addFragment(GrowthNoteCompleteFragment(growthNoteModel))


            //selectedFeeling, 글쓴 내용 같이 이동
        }
        return binding.root
    }


    fun isActivatedButton(
        binding: GrowthNoteEditLayoutBinding
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