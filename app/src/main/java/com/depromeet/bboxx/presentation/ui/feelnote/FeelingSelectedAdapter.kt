package com.depromeet.bboxx.presentation.ui.feelnote

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.bboxx.databinding.ItemResultSelectedFeelingBinding
import com.depromeet.bboxx.presentation.extension.loadUrl
import com.depromeet.bboxx.presentation.model.SelectFeelingEmotionModel


class FeelingSelectedAdapter(val context: Context) :
    RecyclerView.Adapter<FeelingSelectedAdapter.Holder>() {
    var listData = mutableListOf<SelectFeelingEmotionModel>()

    fun setData(dataList: MutableList<SelectFeelingEmotionModel>) {
        listData.clear()
        listData.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemResultSelectedFeelingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return Holder(binding)

    }


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setData(listData[position])
    }


    override fun getItemCount(): Int {
        return listData.size
    }


    inner class Holder(val binding: ItemResultSelectedFeelingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(data: SelectFeelingEmotionModel) {
            binding.ivFeeling.loadUrl(data.emotionUrl)
        }

    }

}