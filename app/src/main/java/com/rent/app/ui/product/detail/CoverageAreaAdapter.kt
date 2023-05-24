package com.rent.app.ui.product.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rent.app.R
import com.rent.app.databinding.ItemAreaPengirimanBinding

class CoverageAreaAdapter(
    private val items: ArrayList<String>,
    private val onItemClick: (position: Int) -> Unit
) :
    RecyclerView.Adapter<CoverageAreaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_area_pengiriman, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind(items[position])
            holder.itemView.setOnClickListener { onItemClick(position) }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemViewHolder(private var itemBinding: ItemAreaPengirimanBinding) : ViewHolder(itemBinding) {
        fun bind(item: String?) {
            itemBinding.title = item
            itemBinding.executePendingBindings()
        }
    }

    open class ViewHolder(viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root)

}
