package com.rent.app.ui.main.child.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.crocodic.core.helper.ToastHelper
import com.rent.app.R
import com.rent.app.data.product.Cart
import com.rent.app.databinding.ItemCartBinding
import com.rent.app.util.calendar.CalendarUtils
import com.rent.app.util.ext.onChangeText

class CartAdapter(
    private val items: ArrayList<Cart>,
    private val fragment : CartFragment,
    private val onItemClick: (position: Int) -> Unit
) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_cart,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind(items[position])
            holder.itemView.setOnClickListener { onItemClick(position) }

            holder.ivCheck.setOnClickListener {
                items[position].isSelected = !items[position].isSelected
                notifyItemChanged(position)
                fragment.updateCheckAll()
            }

            holder.ivDelete.setOnClickListener {
                notifyItemRemoved(position)
                items.removeAt(position)
                //todo when list is empty call empty state on fragment
            }

            holder.tvPeriode.setOnClickListener {
                ToastHelper(context).showAToast("Show Date Range Picker")
            }

            holder.ivPlus.setOnClickListener {

                if (items[position].qty < items[position].product.stock) {

                    items[position].qty++
                    val strQty = holder.etQty.text.toString()

                    if (strQty.isEmpty())
                        items[position].qty = 0

                    notifyItemChanged(position)
                    fragment.updateCheckAll()
                }
            }

            holder.ivMinus.setOnClickListener {


                val strQty = holder.etQty.text.toString()

                if (strQty.isEmpty() || items[position].qty <= 1) {

                } else {
                    items[position].qty--
                    notifyItemChanged(position)
                }
                fragment.updateCheckAll()

            }

            holder.etQty.onChangeText {
                val strQty = it
                try {
                    val intQty = Integer.parseInt(strQty)
                    items[position].qty = intQty
                } catch (e: NumberFormatException) {

                }
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class ItemViewHolder(private var itemBinding: ItemCartBinding) : ViewHolder(itemBinding) {
        val ivCheck = itemBinding.ivCheck
        val ivDelete = itemBinding.ivDelete
        val tvPeriode = itemBinding.tvPeriode
        val ivPlus = itemBinding.ivPlus
        val ivMinus = itemBinding.ivMinus
        val etQty = itemBinding.etQty
        fun bind(item: Cart?) {
            itemBinding.cart = item
            item?.let {
                itemBinding.periodeDate = "${
                    CalendarUtils.setFormatDate(
                        it.startDate,
                        CalendarUtils.SERVER_DATE_FORMAT,
                        CalendarUtils.VIEW_DATE_PERIOD_FORMAT
                    )
                } - " +
                        CalendarUtils.setFormatDate(
                            it.endDate,
                            CalendarUtils.SERVER_DATE_FORMAT,
                            CalendarUtils.VIEW_DATE_PERIOD_FORMAT
                        )
                itemBinding.subTotal = it.product.price * checkSumDay(it)
            }
        }

        fun checkSumDay(item: Cart): Int {
            val sumDate = CalendarUtils.sumRangeDate(item.startDate, item.endDate)
            return sumDate + 1
        }
    }

    open class ViewHolder(viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)


}
