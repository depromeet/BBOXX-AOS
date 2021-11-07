package com.depromeet.bboxx.presentation.ui.feelnote

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.ItemTroubleNameBinding
import com.depromeet.bboxx.presentation.model.FeelingName

class FeelingNoteSelectBottomAdapter(val itemClick: (String)-> Unit): RecyclerView.Adapter<FeelingNoteSelectBottomAdapter.ViewHolder>() {

    private val troubleList = mutableListOf<FeelingName>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_trouble_name,
            parent,
            false
        )
    ).apply {
        itemView.setOnClickListener {
            it.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor("#2C2C2C"))
            itemClick(troubleList[adapterPosition].troubleName)
        }
    }

    override fun getItemCount() = troubleList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = troubleList[position]
        holder.apply {
            bind(item)
        }
    }

    fun setFeelList(list: List<FeelingName>){
        //  adaper clear and data add
        troubleList.clear()
        troubleList.addAll(list)
        //  recyclerview set Data Change
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemTroubleNameBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(nameInfo: FeelingName) {
            binding.apply {
                vm = nameInfo
                executePendingBindings()
            }
        }
    }
}