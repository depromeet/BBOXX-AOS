package com.depromeet.bboxx.presentation.ui.growthNote

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.LayoutFeelingCardViewBinding
import com.depromeet.bboxx.domain.model.ImprovementDiaries
import com.depromeet.bboxx.presentation.ui.MainActivity
import com.google.android.material.chip.Chip


class CardViewAdapter(val context: MainActivity) : RecyclerView.Adapter<CardViewAdapter.Holder>() {
    var listData = mutableListOf<ImprovementDiaries>()

    val bgList = arrayOf(
        R.color.card_view_1,
        R.color.card_view_2,
        R.color.card_view_3,
        R.color.card_view_4,
        R.color.card_view_5,
        R.color.card_view_6,
        R.color.card_view_7,
        R.color.card_view_8,
        R.color.card_view_9,
        R.color.card_view_10,
        R.color.card_view_11
    )

    fun setData(dataList: List<ImprovementDiaries>) {
        listData.clear()
        listData.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val binding =
            LayoutFeelingCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val member = listData[position]
        holder.setData(member, bgList[position % 11])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class Holder(val binding: LayoutFeelingCardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(data: ImprovementDiaries, color: Int) {
            binding.tvTitle.text = data.title
            binding.tvMainText.text = data.content

            binding.clBg.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(context, color))

            binding.ivGradation.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(context, color))

            //val dummyTexts = arrayListOf<String>("나 왜그랬지", "이불킥 각", "개웃겨", "용기파워","난 너무 멋져")
            val tagList = arrayListOf<String>()
            tagList.addAll(data.tags)
            tagList.forEach {
                val chip = Chip(context)
                chip.text = it
                chip.textSize = 14F
                chip.setTextColor(Color.parseColor("#ffffff"))
                chip.chipBackgroundColor =
                    ColorStateList.valueOf(ContextCompat.getColor(context, R.color.gray_10))
                binding.chipGroup.addView(chip)
            }

            binding.clBg.setOnClickListener {
                context.addFragment(GrowthNoteViewerFragment(color, data.emotionDiaryId))

            }
        }

    }
}



