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
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.FeelingNoteSelectFeelingLayoutBinding
import com.depromeet.bboxx.presentation.extension.observeNonNull
import com.depromeet.bboxx.presentation.model.FeelingEmotionModel
import com.depromeet.bboxx.presentation.ui.MainActivity


class FeelingNoteSelectFeelingFragment(val categoryId: Int, val selectedFeeling: String, val title : String, val main : String) : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var adapter: FeelingNoteSelectFeelingAdapter
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    val selectFeeling = ArrayList<tempFeeling>()

    val selectFeelingModel = ArrayList<FeelingEmotionModel>()

    private var tempSaveCategoryId: Int = -1

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FeelingNoteSelectFeelingLayoutBinding.inflate(inflater, container, false)

        mainActivity.getEmotionListImage()

        adapter = FeelingNoteSelectFeelingAdapter(object :
            FeelingNoteSelectFeelingAdapter.dataSelectCallback {
            override fun callback(data: tempFeeling) {
                selectFeeling.add(data)
                setBtnActivated(binding)
            }

            override fun callFeelback(data: FeelingEmotionModel) {
                selectFeelingModel.add(data)
                setBtnActivated(binding)
            }

        })
        binding.rlGrid.adapter = adapter

        val layoutManager = GridLayoutManager(mainActivity, 3)
        layoutManager.orientation = RecyclerView.HORIZONTAL
        binding.rlGrid.layoutManager = layoutManager
        ///setAdapterData()

        //  Emotion API 작업중 from.중근
        mainActivity.feelingNoteViewModel.feelingEmotionList.observeNonNull(this){
            val dataList = ArrayList<FeelingEmotionModel>()
            it.forEach { data ->
                dataList.add(FeelingEmotionModel(data.emotionUrl, data.drawableid, data.id, data.text, data.isSelected))
                if(it.size != it.size-1) {
                    dataList.add(FeelingEmotionModel(data.emotionUrl, 0, data.id,"0", data.isSelected))
                }
            }
            adapter.setContex(mainActivity)
            adapter.setFeelData(dataList)

        }
        tempSaveCategoryId = categoryId

//        binding.clTopView.setBackBtn(object : OnClickListener)
//        binding.clTopView.setBackBtn(object : CustomTopView.OnclickCallback {
//            override fun callback() {
//
//                mainActivity.clearThisFragment(this@FeelingNoteSelectFeelingFragment)
//            }
//
//        })


        return binding.root
    }


    private fun setAdapterData() {
        val dataList = ArrayList<tempFeeling>()
        dataList.add(tempFeeling(R.drawable.feeling_1, "귀찮아"))
        dataList.add(tempFeeling(0, ""))
        dataList.add(tempFeeling(R.drawable.feeling_2, "답답해"))
        dataList.add(tempFeeling(0, ""))
        dataList.add(tempFeeling(R.drawable.feeling_3, "불안해"))
        dataList.add(tempFeeling(0, ""))
        dataList.add(tempFeeling(R.drawable.feeling_4, "서운해"))
        dataList.add(tempFeeling(0, ""))
        dataList.add(tempFeeling(R.drawable.feeling_5, "승질나"))
        dataList.add(tempFeeling(0, ""))
        dataList.add(tempFeeling(R.drawable.feeling_6, "억울해"))
        dataList.add(tempFeeling(0, ""))
        dataList.add(tempFeeling(R.drawable.feeling_7, "열받아"))
        dataList.add(tempFeeling(0, ""))
        dataList.add(tempFeeling(R.drawable.feeling_8, "외로워"))
        dataList.add(tempFeeling(0, ""))
        dataList.add(tempFeeling(R.drawable.feeling_9, "울고싶어"))
        dataList.add(tempFeeling(0, ""))
        dataList.add(tempFeeling(R.drawable.feeling_10, "재수없어"))
        dataList.add(tempFeeling(0, ""))
        dataList.add(tempFeeling(R.drawable.feeling_11, "창피해"))
        dataList.add(tempFeeling(0, ""))
        dataList.add(tempFeeling(R.drawable.feeling_12, "현타와"))
        dataList.add(tempFeeling(0, ""))
        dataList.add(tempFeeling(R.drawable.feeling_13, "힘들어"))

        adapter.setContex(mainActivity)

        adapter.setData(dataList)


    }

    fun setBtnActivated(binding: FeelingNoteSelectFeelingLayoutBinding) {
        if (!selectFeeling.isNullOrEmpty()) {
            binding.btnSuccess.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor("#2C2C2C"))
            binding.btnSuccess.setTextColor(Color.parseColor("#ffffff"))
            binding.btnSuccess.setOnClickListener {
                selectFeeling.forEachIndexed { index, tempFeeling ->
                    Log.d("HAE", index.toString() + "번쨰" + tempFeeling.text)
                }

                mainActivity.addFragment(FeelingNoteResultFragment(tempSaveCategoryId, selectedFeeling, selectFeeling, title, main))

                //   수정하다... 너무 제가 많이 거드리는것 같아서.. 멈췄습니다...
                //mainActivity.addFragment(FeelingNoteResultFragment(tempSaveCategoryId, selectedFeeling, selectFeelingModel, title, main))

            }

        }
    }


    data class tempFeeling(
        val drawableid: Int,
        val text: String,
        var isSelected: Boolean = false
    )
}

