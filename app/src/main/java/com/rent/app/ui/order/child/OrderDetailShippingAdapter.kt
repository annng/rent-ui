package com.rent.app.ui.order.child

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rent.app.R
import com.rent.app.databinding.ItemOrderDetailShippingBinding

class OrderDetailShippingAdapter(
    private val values: ArrayList<String>,
    private val titles: ArrayList<String>,
    private val onItemClick: (position: Int) -> Unit
) :
    RecyclerView.Adapter<OrderDetailShippingAdapter.ViewHolder>() {
    lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       context = parent.context
        return ItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_order_detail_shipping, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind(titles[position], values[position])
            holder.itemView.setOnClickListener { onItemClick(position) }
        }
    }

    override fun getItemCount(): Int {
        return values.size
    }

    class ItemViewHolder(private var itemBinding: ItemOrderDetailShippingBinding) : ViewHolder(itemBinding) {
        fun bind(title : String?, value: String?) {
            itemBinding.label = title
            itemBinding.shipping = value
            itemBinding.executePendingBindings()
        }
    }

    open class ViewHolder(viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root)

}
