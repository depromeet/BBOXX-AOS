package com.depromeet.bboxx.presentation.ui.feelhistory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.depromeet.bboxx.databinding.ItemAlarmHistoryBinding
import com.depromeet.bboxx.domain.model.Notifications
import com.depromeet.bboxx.presentation.base.BaseAdapter
import com.depromeet.bboxx.presentation.base.BaseHolder
import javax.inject.Inject

class FeelHistoryAdapter
@Inject constructor() :
    BaseAdapter<Notifications>(NotificationItemCallback()) {

    private var onClickListener: UserClickEvent? = null

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<out ViewDataBinding, Notifications> {
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

    fun replaceItems(items: List<Notifications>) {
        submitList(items)
    }

    fun setOnClickListener(onClickListener: UserClickEvent) {
        this.onClickListener = onClickListener
    }

    inner class NotificationInformationHolder(binding: ItemAlarmHistoryBinding) :
        BaseHolder<ItemAlarmHistoryBinding, Notifications>(binding) {
        override fun bind(element: Notifications) {
            super.bind(element)
            binding.modelItem = element
            binding.executePendingBindings()

            binding.layAlarmMain.setOnClickListener {
                onClickListener?.onItemClick(element, itemId)
            }

            binding.btnRemove.setOnClickListener {
                onClickListener?.onItemDeleteClick(element, itemId)
            }
        }
    }

    class NotificationItemCallback : DiffUtil.ItemCallback<Notifications>() {
        override fun areItemsTheSame(oldItem: Notifications, newItem: Notifications): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Notifications, newItem: Notifications): Boolean {
            return oldItem == newItem
        }
    }
}