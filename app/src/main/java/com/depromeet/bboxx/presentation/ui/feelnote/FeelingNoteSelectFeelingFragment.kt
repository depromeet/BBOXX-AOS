package com.depromeet.bboxx.presentation.ui.feelnote

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.FeelingNoteSelectFeelingLayoutBinding
import com.depromeet.bboxx.presentation.ui.MainActivity


class FeelingNoteSelectFeelingFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    val adapter = FeelingNoteSelectFeelingAdapter()
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

        val binding = FeelingNoteSelectFeelingLayoutBinding.inflate(inflater, container, false)


        binding.rlGrid.adapter = adapter

        val layoutManager = GridLayoutManager(mainActivity, 3)
        layoutManager.orientation = RecyclerView.HORIZONTAL
        binding.rlGrid.layoutManager = layoutManager
        setAdapterData()
        return binding.root
    }


    fun setAdapterData() {
        //TODO HAERIN Array로 빼기
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



    data class tempFeeling(
        val drawableid : Int,
        val text : String
    )
}

