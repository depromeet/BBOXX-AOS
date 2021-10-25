package com.depromeet.bboxx.presentation.ui.feelhistory

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.bboxx.presentation.model.ViewTypeModel
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

class FeelHistoryAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items = ArrayList<ViewTypeModel>()
    val delegateAdapter = AdapterDelegatesManager<List<ViewTypeModel>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        delegateAdapter.onCreateViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapter.onBindViewHolder(items, position, holder)
    }

    override fun getItemViewType(position: Int) =
        delegateAdapter.getItemViewType(items, position)


    override fun getItemCount() = items.size


}