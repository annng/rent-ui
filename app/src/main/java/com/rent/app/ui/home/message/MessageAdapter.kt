package com.rent.app.ui.home.message

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rent.app.R
import com.rent.app.data.home.Message
import com.rent.app.databinding.ItemMessageLeftBinding
import com.rent.app.databinding.ItemMessageRightBinding
import com.rent.app.util.calendar.CalendarUtils

class MessageAdapter(
    private val items: ArrayList<Message>,
    private val onItemClick: (position: Int) -> Unit
) :
    RecyclerView.Adapter<MessageAdapter.ViewHolder>() {
    lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       context = parent.context
        return if (viewType == 1)
            ItemViewHolderLeft(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_message_left, parent, false))
        else
            ItemViewHolderRight(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_message_right, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ItemViewHolderLeft) {
            holder.bind(items[position])
        }else if (holder is ItemViewHolderRight){
            holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {

        return if (items[position].isAdmin){
            1
        }else {
            0
        }
    }

    class ItemViewHolderLeft(private var itemBinding: ItemMessageLeftBinding) : ViewHolder(itemBinding) {
        fun bind(item: Message?) {
            itemBinding.sender = item?.getSender()
            itemBinding.createAt = item?.date?.let { CalendarUtils.setFormatDate(it, CalendarUtils.SERVER_DATE_TIME_FORMAT, CalendarUtils.VIEW_DATE_TIME_FORMAT) }
            itemBinding.message = item?.message
            itemBinding.executePendingBindings()
        }
    }

    class ItemViewHolderRight(private var itemBinding: ItemMessageRightBinding) : ViewHolder(itemBinding) {
        fun bind(item: Message?) {
            itemBinding.sender = item?.getSender()
            itemBinding.createAt = item?.date?.let { CalendarUtils.setFormatDate(it, CalendarUtils.SERVER_DATE_TIME_FORMAT, CalendarUtils.VIEW_DATE_TIME_FORMAT) }
            itemBinding.message = item?.message
            itemBinding.executePendingBindings()
        }
    }

    open class ViewHolder(viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root)

}
