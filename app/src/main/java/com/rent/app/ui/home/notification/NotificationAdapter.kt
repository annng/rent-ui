package com.rent.app.ui.home.notification

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rent.app.R
import com.rent.app.data.home.Notification
import com.rent.app.databinding.ItemNotifikasiBinding
import com.rent.app.util.calendar.CalendarUtils
import com.rent.app.util.ext.setColorDrawable
import java.util.*

class NotificationAdapter(
    private val items: ArrayList<Notification>,
    private val onItemClick: (position: Int) -> Unit
) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {
    lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       context = parent.context
        return ItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_notifikasi, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind(items[position])
            holder.itemView.setOnClickListener { onItemClick(position) }
            val item = items[position]
            if (item.isOrder()) {
                item.order?.let {
                    holder.tvStatus.background.setColorDrawable(context, it.getBackgroundStatus())
                    holder.tvStatus.setTextColor(ContextCompat.getColor(context, it.getTextColorStatus()))
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemViewHolder(private var itemBinding: ItemNotifikasiBinding) : ViewHolder(itemBinding) {
        val tvStatus = itemBinding.tvStatus
        fun bind(item: Notification?) {
            itemBinding.notification = item
            itemBinding.notificationDate = item?.let { CalendarUtils.setFormatDate(it.createdAt, CalendarUtils.SERVER_DATE_TIME_FORMAT, CalendarUtils.VIEW_DATE_TIME_FORMAT) }
            itemBinding.invoiceDate = item?.order?.let { CalendarUtils.setFormatDate(it.invoiceDate, CalendarUtils.SERVER_DATE_TIME_FORMAT, CalendarUtils.VIEW_DATE_TIME_FORMAT) }
            itemBinding.executePendingBindings()
        }
    }

    open class ViewHolder(viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root)

}
