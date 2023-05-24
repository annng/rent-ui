package com.rent.app.ui.main.child.home.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rent.app.R
import com.rent.app.data.home.SectionProduct
import com.rent.app.databinding.ItemParentProductBinding
import com.rent.app.ui.product.detail.ProductDetailActivity
import com.rent.app.util.ext.initItemHorizontal
import kotlinx.android.synthetic.main.item_parent_product.view.*

class SectionProductAdapter(
    private val items: ArrayList<SectionProduct>,
    private val onItemClick: (position: Int) -> Unit
) :
    RecyclerView.Adapter<SectionProductAdapter.ViewHolder>() {

    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_parent_product,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind(items[position])
            holder.itemView.setOnClickListener { onItemClick(position) }
            val view = holder.itemView
            val products = items[position].products
            val adapter = ProductAdapter(products) {
                val i = Intent(context, ProductDetailActivity::class.java)
                context.startActivity(i)
            }
            view.rvProduct.initItemHorizontal(context, adapter)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemViewHolder(private var itemBinding: ItemParentProductBinding) :
        ViewHolder(itemBinding) {
        fun bind(item: SectionProduct?) {
            itemBinding.item = item
            itemBinding.executePendingBindings()
        }
    }

    open class ViewHolder(viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)

}
