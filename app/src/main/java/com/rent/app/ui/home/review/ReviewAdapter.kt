package com.rent.app.ui.home.review

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rent.app.R
import com.rent.app.data.home.Review
import com.rent.app.databinding.ItemReviewBinding
import com.rent.app.util.calendar.CalendarUtils

class ReviewAdapter(
    private val items: ArrayList<Review>,
    private val onItemClick: (position: Int) -> Unit
) :
    RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_review,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind(items[position])
            holder.itemView.setOnClickListener { onItemClick(position) }

            createRating(items[position].rating, holder.llRating)
        }
    }

    fun createRating(rating: Int, llRating: LinearLayout) {
        llRating.removeAllViews()
        for (i in 0 until rating) {
            var imageView = ImageView(context)
            var vp = LinearLayout.LayoutParams(36, 36)
            imageView.layoutParams = vp
            imageView.setPadding(2, 0, 0, 2)

            imageView.setImageResource(R.drawable.ic_orange_filled_star)

            llRating.addView(imageView)
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }

    class ItemViewHolder(private var itemBinding: ItemReviewBinding) : ViewHolder(itemBinding) {
        val llRating = itemBinding.llRating
        fun bind(item: Review?) {
            itemBinding.review = item
            item?.let {
                itemBinding.createdAt = CalendarUtils.setFormatDate(
                    it.createdAt,
                    CalendarUtils.SERVER_DATE_FORMAT,
                    CalendarUtils.VIEW_DATE_FORMAT
                )
            }
            itemBinding.executePendingBindings()
        }
    }

    open class ViewHolder(viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)

}
