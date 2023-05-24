package com.rent.app.ui.main.child.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rent.app.R
import com.rent.app.data.product.Product
import com.rent.app.databinding.ItemProductBinding

class ProductAdapter(
    private val items: ArrayList<Product>,
    private val onItemClick: (position: Int) -> Unit
) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_product, parent, false))
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

    class ItemViewHolder(private var itemBinding: ItemProductBinding) : ViewHolder(itemBinding) {
        fun bind(item: Product?) {
            itemBinding.item = item
            itemBinding.executePendingBindings()
        }
    }

    open class ViewHolder(viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root)

}
