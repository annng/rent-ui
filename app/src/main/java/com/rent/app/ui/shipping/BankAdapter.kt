package com.rent.app.ui.shipping

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rent.app.R
import com.rent.app.data.order.Bank
import com.rent.app.databinding.ItemBankBinding

class BankAdapter(
    private val items: ArrayList<Bank>,
    private val onItemClick: (position: Int) -> Unit
) :
    RecyclerView.Adapter<BankAdapter.ViewHolder>() {
    lateinit var context : Context
    var selectedPosition : Int = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       context = parent.context
        return ItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_bank, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind(items[position])
            holder.itemView.setOnClickListener { onItemClick(position) }
            if (position == selectedPosition){
                holder.llContent.setBackgroundResource(R.drawable.bg_border_selected)
            }else{
                holder.llContent.setBackgroundResource(R.drawable.bg_border_deselected)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemViewHolder(private var itemBinding: ItemBankBinding) : ViewHolder(itemBinding) {
        val llContent = itemBinding.llContent
        fun bind(item: Bank?) {
            itemBinding.bank = item
            itemBinding.executePendingBindings()
        }
    }

    open class ViewHolder(viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root)

}
