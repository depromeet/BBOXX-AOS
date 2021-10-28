package com.depromeet.bboxx.presentation.ui.feelnote

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.bboxx.databinding.ItemSelectFeelingBinding

class FeelingNoteSelectFeelingAdapter() : RecyclerView.Adapter<Holder>() {
    var listData = mutableListOf<FeelingNoteSelectFeelingFragment.tempFeeling>()

    var context: Context? = null
    fun setData(dataList: ArrayList<FeelingNoteSelectFeelingFragment.tempFeeling>) {
        listData.clear()
        listData.addAll(dataList)
        notifyDataSetChanged()
    }

    fun setContex(context: Context) {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val binding =
            ItemSelectFeelingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)

    }


    override fun onBindViewHolder(holder: Holder, position: Int) {
        val member = listData[position]
        val relativeParams = LinearLayout.LayoutParams(
            dpToPx(160),
            dpToPx(160)
        )
        when (position % 3) {
            0 -> {

                relativeParams.setMargins(20, 0, 0, 0) // left, top, right, bottom
                holder.itemView.setLayoutParams(relativeParams)
            }
            1 -> {

                relativeParams.setMargins(20, -50, 0, 0) // left, top, right, bottom

                holder.itemView.setLayoutParams(relativeParams)
            }
            2 -> {

                relativeParams.setMargins(20, -100, 0, 0) // left, top, right, bottom

                holder.itemView.setLayoutParams(relativeParams)
            }
        }


        holder.setData(member, context)
    }

    private fun dpToPx(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context?.resources?.displayMetrics
        )
            .toInt()
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}

class Holder(val binding: ItemSelectFeelingBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setData(data: FeelingNoteSelectFeelingFragment.tempFeeling, context: Context?) {
        if (data.drawableid == 0) {
            binding.clBg.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#00ff0000"))

            binding.tvFeeling.visibility = View.GONE
            binding.ivFeeling.visibility = View.GONE
        } else {
            binding.clBg.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#ffffff"))

            binding.tvFeeling.visibility = View.VISIBLE
            binding.ivFeeling.visibility = View.VISIBLE
            context?.let {
                binding.ivFeeling.background = ContextCompat.getDrawable(it, data.drawableid)

            }
            binding.tvFeeling.text = data.text
        }


    }

}

