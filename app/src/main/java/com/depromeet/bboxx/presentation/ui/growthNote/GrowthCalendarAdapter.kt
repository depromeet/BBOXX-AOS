package com.depromeet.bboxx.presentation.ui.growthNote

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.bboxx.R
import com.depromeet.bboxx.databinding.ItemCalendarBinding
import com.depromeet.bboxx.presentation.model.SelectCalendarModel

class GrowthCalendarAdapter(val itemClick: (String)-> Unit, val msg: (Boolean)->Unit): RecyclerView.Adapter<GrowthCalendarAdapter.ViewHolder>() {

    private val calendarList = mutableListOf<SelectCalendarModel>()

    class ViewHolder(val binding: ItemCalendarBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(calendar: SelectCalendarModel) {
            binding.apply {
                 itemCalendar = calendar
                executePendingBindings()
            }
        }
    }

    fun setDataList(list: List<SelectCalendarModel>){
        calendarList.clear()
        calendarList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_calendar,
            parent,
            false
        )
    ).apply {
        itemView.setOnClickListener {
            if(calendarList[adapterPosition].isClicked){
                itemClick(calendarList[adapterPosition].month.toString())
            }
            else{
                msg(false)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = calendarList[position]
        holder.apply {
            bind(item)
        }
    }

    override fun getItemCount()= calendarList.size
}