package com.depromeet.bboxx.presentation.ui.growthNote

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.bboxx.presentation.base.BaseAdapter
import com.depromeet.bboxx.data.entity.ImprovementDiariesEntity
import com.depromeet.bboxx.databinding.LayoutFeelingCardViewBinding


class CardViewAdapter : RecyclerView.Adapter<Holder>() {
    var listData = mutableListOf<ImprovementDiariesEntity>()
    val bgList = arrayOf<String>("#A45D63", "#C6815B", "#CBA367", "#687855", "#5B7A6B", "#637C94", "#505474", "#776181", "#5F5B6D", "#978A7B", "5E4E41")
    fun setData(dataList: ArrayList<ImprovementDiariesEntity>) {
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
        holder.setData(member)
        holder.setBg(bgList[position % 11])
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}

class Holder(val binding: LayoutFeelingCardViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setData(data: ImprovementDiariesEntity) {
        binding.tvTitle.text = data.title
        binding.tvMainText.text = data.content
//        binding.textView.text = member.name
    }

    fun setBg(color: String) {
        binding.clBg.backgroundTintList =
            ColorStateList.valueOf(Color.parseColor(color))

        binding.ivGradation.backgroundTintList =
            ColorStateList.valueOf(Color.parseColor(color))

    }
}

