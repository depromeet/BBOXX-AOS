package com.example.navermoviesearch.presentation.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.navermoviesearch.presentation.extension.getItem
import java.util.ArrayList

abstract class BaseAdapter<D, VH : BaseViewHolder> : RecyclerView.Adapter<VH>() {
    private var list = ArrayList<D>()

    fun addItem(items: List<D>) {
        list.addAll(items)
    }

    fun removeItems() {
        list.clear()
    }

    abstract fun getItemViewId(): Int

    abstract fun instantiateViewHolder(view: View): VH

    fun getItem(position: Int): D?{
        return list.getItem(position)
    }

    fun getItems() = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(getItemViewId(), parent, false)
        return instantiateViewHolder(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = onBind(holder, position)
    abstract fun onBind(holder: VH, position: Int)
    override fun getItemCount() = list.size


}