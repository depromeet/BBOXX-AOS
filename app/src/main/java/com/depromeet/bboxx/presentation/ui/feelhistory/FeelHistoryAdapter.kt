package com.depromeet.bboxx.presentation.ui.feelhistory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.depromeet.bboxx.databinding.ItemAlarmHistoryBinding
import com.depromeet.bboxx.presentation.base.BaseAdapter
import com.depromeet.bboxx.presentation.base.BaseHolder
import com.depromeet.bboxx.presentation.model.NotificationModel
import javax.inject.Inject

class FeelHistoryAdapter
@Inject constructor() :
    BaseAdapter<NotificationModel>(NotificationItemCallback()) {

    private var onClickListener: UserClickEvent? = null
    private var notificationList = mutableListOf<NotificationModel>()
    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<out ViewDataBinding, NotificationModel> {
        return NotificationInformationHolder(
            ItemAlarmHistoryBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).id.toLong()
    }

    fun replaceItems(items: MutableList<NotificationModel>) {
        notificationList.addAll(items.sortedByDescending { it.id })
        submitList(items.sortedByDescending { it.id })
    }

    fun deleteStatusVisible(){
        deleteStatusChange(true)
    }

    fun deleteStatusGone(){
        deleteStatusChange(false)
    }

    private fun deleteStatusChange(isDelete: Boolean){
        val renew = arrayListOf<NotificationModel>()

        notificationList.forEach {
            val notifications = NotificationModel(it.createAt,it.emotionDiaryId,it.id,it.message, it.receiverId, it.state,it.title, it.updateAt, isDelete, it.beforeDate)
            renew.add(notifications)
        }

        notificationList.clear()
        notificationList.addAll(renew)
        renew.clear()

        submitList(notificationList.sortedByDescending { it.id })
    }

    fun setOnClickListener(onClickListener: UserClickEvent) {
        this.onClickListener = onClickListener
    }

    inner class NotificationInformationHolder(binding: ItemAlarmHistoryBinding) :
        BaseHolder<ItemAlarmHistoryBinding, NotificationModel>(binding) {
        override fun bind(element: NotificationModel) {
            super.bind(element)
            binding.modelItem = element
            binding.executePendingBindings()

            binding.layAlarmMain.setOnClickListener {
                onClickListener?.onItemClick(element, adapterPosition)
            }

            binding.btnRemove.setOnClickListener {
                notificationList.removeAt(adapterPosition)
                onClickListener?.onItemDeleteClick(element, adapterPosition)
            }
        }
    }

    class NotificationItemCallback : DiffUtil.ItemCallback<NotificationModel>() {
        override fun areItemsTheSame(oldItem: NotificationModel, newItem: NotificationModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NotificationModel, newItem: NotificationModel): Boolean {
            return oldItem == newItem
        }
    }
}