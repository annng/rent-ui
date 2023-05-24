package com.rent.app.ui.main.child.order

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rent.app.R
import com.rent.app.data.order.Order
import com.rent.app.databinding.ItemBukuSewaBinding
import com.rent.app.util.calendar.CalendarUtils
import com.rent.app.util.ext.setColorDrawable

class OrderAdapter(
    private val items: ArrayList<Order>,
    private val onItemClick: (position: Int) -> Unit
) :
    RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_buku_sewa,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind(items[position])
            holder.itemView.setOnClickListener { onItemClick(position) }

            val item = items[position]
            holder.tvStatus.background.setColorDrawable(context, item.getBackgroundStatus())
            holder.tvStatus.setTextColor(ContextCompat.getColor(context, item.getTextColorStatus()))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemViewHolder(private var itemBinding: ItemBukuSewaBinding) : ViewHolder(itemBinding) {
        val tvStatus = itemBinding.tvStatus
        fun bind(item: Order?) {
            itemBinding.order = item
            item?.let {
                itemBinding.invoiceDate = CalendarUtils.setFormatDate(
                    it.invoiceDate,
                    CalendarUtils.SERVER_DATE_TIME_FORMAT,
                    CalendarUtils.VIEW_DATE_TIME_FORMAT
                )
            }
            itemBinding.executePendingBindings()
        }
    }

    open class ViewHolder(viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)

}
