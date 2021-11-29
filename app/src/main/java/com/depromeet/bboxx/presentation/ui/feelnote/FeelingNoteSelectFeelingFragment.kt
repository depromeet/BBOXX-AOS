package com.depromeet.bboxx.presentation.ui.feelnote

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.bboxx.databinding.FeelingNoteSelectFeelingLayoutBinding
import com.depromeet.bboxx.presentation.extension.observeNonNull
import com.depromeet.bboxx.presentation.model.FeelingEmotionModel
import com.depromeet.bboxx.presentation.model.SelectFeelingEmotionModel
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.ui.result.Result
import com.depromeet.bboxx.presentation.utils.CustomTopView


class FeelingNoteSelectFeelingFragment(
    val categoryId: Int,
    val selectedFeeling: String,
    val title: String,
    val main: String
) : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var adapter: FeelingNoteSelectFeelingAdapter
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    val selectFeelingModel = mutableListOf<SelectFeelingEmotionModel>()

    private var tempSaveCategoryId: Int = 1

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FeelingNoteSelectFeelingLayoutBinding.inflate(inflater, container, false)

        mainActivity.getEmotionListImage()

        if (container != null) {
            initView(binding, container.context)
        }

        return binding.root
    }

    fun initView(binding: FeelingNoteSelectFeelingLayoutBinding, ctx: Context) {

        adapter = FeelingNoteSelectFeelingAdapter(object :
            FeelingNoteSelectFeelingAdapter.dataSelectCallback {
            override fun activeCallback(data: FeelingEmotionModel) {
                selectFeelingModel.add(
                    SelectFeelingEmotionModel(
                        data.emotionUrl,
                        data.drawableid,
                        data.id,
                        data.text,
                        data.isSelected
                    )
                )
                setBtnActivated(binding)
            }

            override fun unActiveCallback(data: FeelingEmotionModel) {
                val selectModel = SelectFeelingEmotionModel(
                    data.emotionUrl,
                    data.drawableid,
                    data.id,
                    data.text,
                    data.isSelected
                )
                var index = 0

                selectFeelingModel.forEach {
                    if(it.text == selectModel.text){
                        selectFeelingModel.removeAt(index)
                        return@forEach
                    }
                    index++
                }

                setBtnActivated(binding)
            }

            override fun limitDataCallback(limitStatus: Boolean) {
                onLimitToast()
            }
        })
        binding.rlGrid.adapter = adapter

        val layoutManager = GridLayoutManager(mainActivity, 3)
        layoutManager.orientation = RecyclerView.HORIZONTAL
        binding.rlGrid.layoutManager = layoutManager

        mainActivity.feelingNoteViewModel.feelingEmotionList.observeNonNull(this) {
            val dataList = ArrayList<FeelingEmotionModel>()
            it.forEach { data ->
                dataList.add(
                    FeelingEmotionModel(
                        data.emotionUrl,
                        1,
                        data.id,
                        data.text,
                        data.isSelected
                    )
                )
                if (it.size != it.size - 1) {
                    dataList.add(FeelingEmotionModel("", 0, data.id, "0", data.isSelected))
                }
            }
            adapter.setContex(mainActivity)
            adapter.setFeelData(dataList)

        }

        mainActivity.feelingNoteViewModel.networkErrorEvent.observeNonNull(this){
            when(it){
                is Result.Error ->{
                    errorEventMsg(it.exception)
                }
            }
        }

        tempSaveCategoryId = categoryId

        binding.clTopView.setBackBtn(object : CustomTopView.OnclickCallback {
            override fun callback() {
                mainActivity.clearThisFragment(this@FeelingNoteSelectFeelingFragment)
            }
        })
    }

    private fun setBtnActivated(binding: FeelingNoteSelectFeelingLayoutBinding) {
        if (selectFeelingModel.size != 0) {
            binding.btnSuccess.isClickable = true

            binding.btnSuccess.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor("#2C2C2C"))
            binding.btnSuccess.setTextColor(Color.parseColor("#ffffff"))
            binding.btnSuccess.setOnClickListener {

                mainActivity.addFragment(
                    FeelingNoteResultFragment(
                        tempSaveCategoryId,
                        selectedFeeling,
                        selectFeelingModel,
                        title,
                        main
                    )
                )
            }
        }
        else{
            binding.btnSuccess.isClickable = false
            binding.btnSuccess.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor("#332C2C2C"))
            binding.btnSuccess.setTextColor(Color.parseColor("#662C2C2C"))
        }
    }

    private fun onLimitToast(){
        Toast.makeText(requireContext(), "최대 5개까지 고를 수 있어.", Toast.LENGTH_SHORT).show()
    }

    private fun errorEventMsg(error: Throwable){
        val errorMsg = error.message
        Toast.makeText(requireContext(), "Error Msg: $errorMsg", Toast.LENGTH_SHORT).show()
    }
}

