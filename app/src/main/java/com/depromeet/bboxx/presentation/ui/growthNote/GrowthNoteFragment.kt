package com.depromeet.bboxx.presentation.ui.growthNote

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.bboxx.R
import com.depromeet.bboxx.data.entity.ImprovementDiariesEntity
import com.depromeet.bboxx.data.entity.ImprovementTagsEntity
import com.depromeet.bboxx.databinding.EmotionDiaryEditLayoutBinding
import com.depromeet.bboxx.databinding.GrowthDiaryBinding
import com.depromeet.bboxx.presentation.ui.BackLayerFragment
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.depromeet.bboxx.presentation.ui.feelnote.FeelingNoteResultFragment
import com.depromeet.bboxx.presentation.utils.CardViewItemDecoration
import com.depromeet.bboxx.presentation.utils.CustomTopView

class GrowthNoteFragment() : Fragment() {

    lateinit var mainActivity: MainActivity

    val mAdapter = CardViewAdapter()

    var a = 0

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

        val binding = GrowthDiaryBinding.inflate(inflater, container, false)



        binding.clTopView.setRightBtn(object :CustomTopView.OnclickCallback{
            override fun callback() {
                TODO("Not yet implemented")
            }

        }, R.drawable.ic_profile)

        binding.rlCardView.adapter = mAdapter

        binding.rlCardView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        val dataList = ArrayList<ImprovementDiariesEntity>()
        val tag = ArrayList<ImprovementTagsEntity>()
        dataList.add(
            ImprovementDiariesEntity(
                "지난 두 달간 써온 회고일기를 오늘 다시 읽어보았다. 얼핏 일기와 비슷해보이긴 하지만 조금 더 객관적이고 디테일하다는 측면에서 확연히 다르다. 나의 복잡다단한 감정을 쏟아내는 내면일기가 아니라, 명확한 질문을 두고 ‘나’ 라는 청자에게 쓰는 외면일기에 가깝다. 내가 무엇을 잘했고 부족했는지, 다음 단계로 나가기 위해서는 어떻게 해야하는지. 몇 주간의 기록을 쭉 훑어보니, 내가 일과 삶에 어떤 방향성을 지니고 싶어하는지가 보이고, 그러기 위해 노력하는 모습도 보여서 뿌듯했다.",
                "",
                1,
                1,
                1,
                tag,
                "타이틀1",
                ""
            )
        )
        dataList.add(ImprovementDiariesEntity("", "", 1, 1, 1, tag, "타이틀2", ""))
        dataList.add(ImprovementDiariesEntity("", "", 1, 1, 1, tag, "타이틀3", ""))
        dataList.add(ImprovementDiariesEntity("", "", 1, 1, 1, tag, "타이틀4", ""))
        dataList.add(ImprovementDiariesEntity("", "", 1, 1, 1, tag, "타이틀5", ""))

        val dataList1 = ArrayList<ImprovementDiariesEntity>()

        binding.ivLeft.setOnClickListener {
            a++
            if (a % 2 == 0) {
                setCardView(binding, dataList)
            } else {
                setCardView(binding, dataList1)

            }
        }

        return binding.root
    }


    fun setCardView(binding: GrowthDiaryBinding, dataList: ArrayList<ImprovementDiariesEntity>) {

        if (dataList.isNullOrEmpty()) {
            binding.rlCardView.visibility = View.GONE
            binding.emptyView.visibility = View.VISIBLE
        } else {
            context?.let {
                binding.rlCardView.addItemDecoration(CardViewItemDecoration(it, dataList.size))

            }
            mAdapter.setData(dataList)
            binding.rlCardView.visibility = View.VISIBLE
            binding.emptyView.visibility = View.GONE
        }

    }
}