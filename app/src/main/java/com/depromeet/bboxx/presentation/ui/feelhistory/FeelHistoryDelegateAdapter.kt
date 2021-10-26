package com.depromeet.bboxx.presentation.ui.feelhistory

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.bboxx.R
import com.depromeet.bboxx.presentation.base.BaseViewHolder
import com.depromeet.bboxx.presentation.extension.inflateLayout
import com.depromeet.bboxx.presentation.model.NotificationViewTypeModel
import com.depromeet.bboxx.presentation.model.ViewTypeModel
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate


class FeelHistoryDelegateAdapter: AdapterDelegate<List<ViewTypeModel>>() {

    override fun isForViewType(items: List<ViewTypeModel>, position: Int): Boolean {
        return items[position] is NotificationViewTypeModel
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(
            parent.context.inflateLayout(
                R.layout.item_alarm_history,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        items: List<ViewTypeModel>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position] as NotificationViewTypeModel
        val viewHolder = holder as ViewHolder
        with(viewHolder){
            item.createAt
            item.title
        }
    }

    class ViewHolder(itemView: View) : BaseViewHolder(itemView)
}