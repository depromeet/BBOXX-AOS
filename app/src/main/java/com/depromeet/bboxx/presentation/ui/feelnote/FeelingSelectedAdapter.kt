package com.depromeet.bboxx.presentation.ui.feelnote

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.bboxx.databinding.ItemResultSelectedFeelingBinding


class FeelingSelectedAdapter(val context: Context) :
    RecyclerView.Adapter<FeelingSelectedAdapter.Holder>() {
    var listData = mutableListOf<FeelingNoteSelectFeelingFragment.tempFeeling>()

    fun setData(dataList: ArrayList<FeelingNoteSelectFeelingFragment.tempFeeling>) {
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
        fun setData(data: FeelingNoteSelectFeelingFragment.tempFeeling) {
            binding.ivFeeling.background = ContextCompat.getDrawable(context, data.drawableid)
        }

    }

}