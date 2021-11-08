package com.depromeet.bboxx.presentation.ui.feelnote

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.bboxx.databinding.FeelingNoteSelectFeelingLayoutBinding
import com.depromeet.bboxx.presentation.extension.observeNonNull
import com.depromeet.bboxx.presentation.model.FeelingEmotionModel
import com.depromeet.bboxx.presentation.model.SelectFeelingEmotionModel
import com.depromeet.bboxx.presentation.ui.MainActivity
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

    val selectFeelingModel = ArrayList<SelectFeelingEmotionModel>()

    private var tempSaveCategoryId: Int = -1

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
            override fun callback(data: FeelingEmotionModel) {
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
        })
        binding.rlGrid.adapter = adapter

        val layoutManager = GridLayoutManager(mainActivity, 3)
        layoutManager.orientation = RecyclerView.HORIZONTAL
        binding.rlGrid.layoutManager = layoutManager

        //  Emotion API 작업중 from.중근
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

        tempSaveCategoryId = categoryId

        binding.clTopView.setBackBtn(object : CustomTopView.OnclickCallback {
            override fun callback() {
                mainActivity.clearThisFragment(this@FeelingNoteSelectFeelingFragment)
            }
        })
    }

    fun setBtnActivated(binding: FeelingNoteSelectFeelingLayoutBinding) {
        if (!selectFeelingModel.isNullOrEmpty()) {
            binding.btnSuccess.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor("#2C2C2C"))
            binding.btnSuccess.setTextColor(Color.parseColor("#ffffff"))
            binding.btnSuccess.setOnClickListener {
                selectFeelingModel.forEachIndexed { index, tempFeeling ->
                    Log.d("HAE", index.toString() + "번쨰" + tempFeeling.text)
                }

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
    }
}

