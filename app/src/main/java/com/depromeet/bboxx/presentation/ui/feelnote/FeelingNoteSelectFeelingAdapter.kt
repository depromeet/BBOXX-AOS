package com.depromeet.bboxx.presentation.ui.feelnote

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.SparseBooleanArray
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.bboxx.databinding.ItemSelectFeelingBinding
import com.depromeet.bboxx.presentation.extension.loadUrl
import com.depromeet.bboxx.presentation.model.FeelingEmotionModel


class FeelingNoteSelectFeelingAdapter(val dataCallback: dataSelectCallback) :
    RecyclerView.Adapter<FeelingNoteSelectFeelingAdapter.Holder>() {

    var context: Context? = null

    var listFeel = mutableListOf<FeelingEmotionModel>()
    var feelingTextFiveLimit = mutableListOf<String>()

    private val mSelectedItems = SparseBooleanArray(0)

    fun setFeelData(dataList: ArrayList<FeelingEmotionModel>){
        listFeel.clear()
        listFeel.addAll(dataList)
        notifyDataSetChanged()
    }

    fun setContex(context: Context) {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val binding =
            ItemSelectFeelingBinding.inflate(LayoutInflater.from(parent.context), parent,
                false)
        return Holder(binding, dataCallback)

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val member = listFeel[position]

        val relativeParams = LinearLayout.LayoutParams(
            dpToPx(160),
            dpToPx(160)
        )
        when (position % 3) {
            0 -> {

                relativeParams.setMargins(20, 0, 0, 0) // left, top, right, bottom
                holder.itemView.layoutParams = relativeParams
            }
            1 -> {

                relativeParams.setMargins(20, -50, 0, 0) // left, top, right, bottom

                holder.itemView.layoutParams = relativeParams
            }
            2 -> {

                relativeParams.setMargins(20, -100, 0, 0) // left, top, right, bottom

                holder.itemView.layoutParams = relativeParams
            }
        }



        holder.setBindData(member, context)
    }

    private fun dpToPx(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context?.resources?.displayMetrics
        )
            .toInt()
    }

    override fun getItemCount() = listFeel.size

    inner class Holder(val binding: ItemSelectFeelingBinding, val callback: dataSelectCallback?) :
        RecyclerView.ViewHolder(binding.root) {

        //  From 중근
        fun setBindData(data: FeelingEmotionModel, context: Context?){
            val position = adapterPosition

            if (data.drawableid == 0) {
                binding.clBg.backgroundTintList =
                    ColorStateList.valueOf(Color.parseColor("#00ff0000"))

                binding.tvFeeling.visibility = View.GONE
                binding.ivFeeling.visibility = View.GONE
            } else {
                binding.clBg.backgroundTintList =
                    ColorStateList.valueOf(Color.parseColor("#ffffff"))

                binding.tvFeeling.visibility = View.VISIBLE
                binding.ivFeeling.visibility = View.VISIBLE

                binding.tvFeeling.text = data.text
                binding.ivFeeling.loadUrl(data.emotionUrl)

                itemView.setOnClickListener {
                    if(binding.tvFeeling.isVisible){
                        if(feelingTextFiveLimit.size < 5){
                            if(activeFeelingStatusCheck(binding, position)){
                                addClickedData(position, binding, callback, data)
                            }
                            else{
                                removeClickedData(position, callback, data)
                            }
                        }
                        else{
                            callback?.let{
                                it.limitDataCallback(false)
                            }
                        }
                    }
                }
            }

            activeStatus(binding, position)
        }
    }

    private fun activeStatus(binding: ItemSelectFeelingBinding, position: Int){
        feelingTextFiveLimit.forEach {
            if(it == listFeel[position].text){
                activeBtn(binding)
            }
        }
    }

    private fun activeFeelingStatusCheck(binding: ItemSelectFeelingBinding, position: Int) : Boolean{
        feelingTextFiveLimit.forEach {
            if(it == listFeel[position].text){
                unActiveBtn(binding)
                return false
            }
        }
        return true
    }

    private fun activeBtn(binding: ItemSelectFeelingBinding){
        binding.clBg.backgroundTintList =
            ColorStateList.valueOf(Color.parseColor("#2C2C2C"))
        binding.tvFeeling.setTextColor(Color.WHITE)

    }

    private fun unActiveBtn(binding: ItemSelectFeelingBinding){
        binding.clBg.backgroundTintList =
            ColorStateList.valueOf(Color.parseColor("#ffffff"))
        binding.tvFeeling.setTextColor(Color.BLACK)
    }

    private fun addClickedData(position: Int, binding: ItemSelectFeelingBinding, callback: dataSelectCallback?, data: FeelingEmotionModel){
        if(!listFeel[position].isSelected){
            activeBtn(binding)

            feelingTextFiveLimit.add(listFeel[position].text)
            listFeel[position].isSelected = true

            callback?.let {
                callback.activeCallback(data)
            }
        }
    }

    private fun removeClickedData(position: Int, callback: dataSelectCallback?, data: FeelingEmotionModel){
        feelingTextFiveLimit.remove(listFeel[position].text)
        listFeel[position].isSelected = false

        callback?.let{
            it.unActiveCallback(data)
        }
    }

    interface dataSelectCallback {
        fun activeCallback(data: FeelingEmotionModel)

        fun unActiveCallback(data: FeelingEmotionModel)

        fun limitDataCallback(limitStatus: Boolean)
    }
}

