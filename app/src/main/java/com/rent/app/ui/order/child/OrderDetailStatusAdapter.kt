package com.rent.app.ui.order.child

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rent.app.R
import com.rent.app.data.order.Order
import com.rent.app.data.order.OrderDetail
import com.rent.app.data.order.OrderStatus
import com.rent.app.databinding.ItemOrderDetailStatusBinding
import com.rent.app.util.calendar.CalendarUtils
import com.rent.app.util.ext.setColorDrawable
import com.rent.app.util.ext.show

class OrderDetailStatusAdapter(
    private val items: ArrayList<OrderStatus>,
    private val orderDetail: OrderDetail,
    private val fragment : OrderDetailStatusFragment,
    private val onItemClick: (position: Int) -> Unit
) :
    RecyclerView.Adapter<OrderDetailStatusAdapter.ViewHolder>() {
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_order_detail_status,
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

            if (position == 0) {
                holder.ivNewestIndicator.visibility = View.VISIBLE
            }

            if (item.statusCode == Order.STATUS_WAITING_PAYMENT || item.statusCode == Order.STATUS_TRANSACTION_COMPLETE) {
                /**
                 * Status Waiting and finish has action button
                 * Waiting -> Button Pay
                 * Finish / Complete -> Button Testimonial / Review
                 */
                holder.btnSubmit.show()

                if (item.statusCode == Order.STATUS_WAITING_PAYMENT) {

                    /**
                     * When order isPaid, pay button will disable
                     */
                    if (orderDetail.isPaid) {
                        holder.btnSubmit.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_btn_status_done)
                        holder.btnSubmit.text = ""
                    } else {
                        holder.btnSubmit.setBackgroundColor(
                            ContextCompat.getColor(
                                context,
                                R.color.orange_dark
                            )
                        )
                        holder.btnSubmit.text = context.resources.getString(R.string.action_bayar)
                        holder.btnSubmit.setOnClickListener {
                            //todo goto payment service
                        }
                    }

                    /**
                     * When payment is expired, button pay will be change become label expired
                     */
                    if (orderDetail.isExpired) {
                        holder.btnSubmit.background.setColorDrawable(context, R.color.gray)
                        holder.btnSubmit.text = context.resources.getString(R.string.action_expired)
                    }


                } else if (item.statusCode == Order.STATUS_TRANSACTION_COMPLETE) {
                    /**
                     * When order isReviewed, button review will show status checked and disable
                     */
                    if (orderDetail.isReviewed) {
                        holder.btnSubmit.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_btn_status_done)
                        holder.btnSubmit.text = ""
                    } else {
                        holder.btnSubmit.setBackgroundColor(
                            ContextCompat.getColor(
                                context,
                                R.color.orange_dark
                            )
                        )
                        holder.btnSubmit.text =
                            context.resources.getString(R.string.action_kirim_testimoni)
                        holder.btnSubmit.setOnClickListener {
                            //todo give review and rating about service
                            fragment.dialogReview()
                        }
                    }
                }
            } else {
                /**
                 * On Status Payment failed, payment success, on delivery, arrive has no button action
                 * ButtonSubmit will be hiding
                 */
                holder.btnSubmit.visibility = View.GONE
            }

            holder.tvStatus.background.setColorDrawable(context, item.getBackgroundStatus())
            holder.tvStatus.setTextColor(ContextCompat.getColor(context, item.getTextColorStatus()))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemViewHolder(private var itemBinding: ItemOrderDetailStatusBinding) :
        ViewHolder(itemBinding) {
        val btnSubmit = itemBinding.btnSubmit
        val ivNewestIndicator = itemBinding.ivNewestIndicator
        val tvStatus = itemBinding.tvStatus
        fun bind(item: OrderStatus?) {
            itemBinding.status = item
            item?.let {
                itemBinding.notificationDate = CalendarUtils.setFormatDate(
                    it.createdAt, CalendarUtils.SERVER_DATE_TIME_FORMAT, CalendarUtils
                        .VIEW_DATE_TIME_FORMAT
                )
            }
            itemBinding.executePendingBindings()
        }
    }

    open class ViewHolder(viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)

}
