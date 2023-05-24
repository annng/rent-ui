package com.rent.app.ui.main.child.account

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rent.app.R
import com.rent.app.data.Menu
import com.rent.app.databinding.ItemMenuAkunBinding
import com.rent.app.util.ext.hide
import com.rent.app.util.ext.show

class AccountAdapter(
    private val items: ArrayList<Menu>,
    private val newMessage : Int,
    private val onItemClick: (position: Int) -> Unit
) :
    RecyclerView.Adapter<AccountAdapter.ViewHolder>() {
    lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       context = parent.context
        return ItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_menu_akun, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind(items[position])
            holder.itemView.setOnClickListener { onItemClick(position) }

            if (position == 2 && newMessage != 0){
                holder.flPesanBadge.show()
                holder.tvSumMessage.text = "$newMessage"
            }else{
                holder.flPesanBadge.hide()
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemViewHolder(private var itemBinding: ItemMenuAkunBinding) : ViewHolder(itemBinding) {
        val flPesanBadge = itemBinding.flPesanBadge
        val tvSumMessage = itemBinding.tvSumMessage
        fun bind(item: Menu?) {
            itemBinding.menu = item
            itemBinding.executePendingBindings()
        }
    }

    open class ViewHolder(viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root)

}
