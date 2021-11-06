package com.depromeet.bboxx.presentation.ui.growthNote

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.ItemSelectFeelTagBinding
import com.depromeet.bboxx.presentation.model.TimelineGrowthTagModel
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.material.chip.Chip

class GrowthNoteTagAdapter(val itemClick: (List<String>)-> Unit, val status: (Boolean) -> Unit): RecyclerView.Adapter<GrowthNoteTagAdapter.ViewHolder>() {

    private val growthTagList = mutableListOf<TimelineGrowthTagModel>()

    private var clickTagList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_select_feel_tag,
            parent,
            false
        )
    ).apply {
        binding.chipName.setOnClickListener {
            if(clickTagList.size in 0..4){
                if(growthTagList[adapterPosition].isSelect){
                    clickTagList.remove(growthTagList[adapterPosition].tag)
                    itemClick(clickTagList)
                    status(false)
                    growthTagList[adapterPosition].isSelect = false
                    chipDisabled(binding.chipName)
                }
                else{
                    clickTagList.add(growthTagList[adapterPosition].tag)
                    itemClick(clickTagList)
                    status(false)
                    growthTagList[adapterPosition].isSelect = true
                    chipActivation(binding.chipName)
                }
            }
            else{
                if(growthTagList[adapterPosition].isSelect){
                    clickTagList.remove(growthTagList[adapterPosition].tag)
                    itemClick(clickTagList)
                    status(false)
                    growthTagList[adapterPosition].isSelect = false
                    chipDisabled(binding.chipName)
                }
                else{
                    status(true)
                }
            }
        }
    }

    private fun chipActivation(chip: Chip){
        chip.setTextColor(Color.WHITE)
        chip.setChipBackgroundColorResource(R.color.mypage_bg)
    }

    private fun chipDisabled(chip: Chip){
        chip.setTextColor(Color.BLACK)
        chip.setChipBackgroundColorResource(R.color.white)
    }

    override fun getItemCount() = growthTagList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = growthTagList[position]
        holder.apply {
            bind(item)
        }
    }

    fun setGrowthTagList(list: List<TimelineGrowthTagModel>){
        growthTagList.clear()
        growthTagList.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemSelectFeelTagBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(nameInfo: TimelineGrowthTagModel) {
            binding.apply {
                vm = nameInfo
                executePendingBindings()
            }

            val lp = binding.chipName.layoutParams
            if( lp is FlexboxLayoutManager.LayoutParams){
                lp.flexGrow = 3f
            }
        }
    }
}