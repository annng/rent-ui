package com.rent.app.widget.slider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.rent.app.R
import com.rent.app.data.home.Review
import com.rent.app.util.ext.loadImageCircle
import com.smarteist.autoimageslider.SliderViewAdapter


class CustomSliderReview(private val items : ArrayList<Review>) : SliderViewAdapter<CustomSliderReview.CustomSliderReviewVH>() {

    lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup): CustomSliderReviewVH {
        context = parent.context
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_slider_review, null)
        return CustomSliderReviewVH(inflate)
    }


    override fun onBindViewHolder(viewHolder: CustomSliderReviewVH, position: Int) {
        val item = items[position]
        viewHolder.ivPhoto.loadImageCircle(item.userPhoto)
        viewHolder.tvName.text = item.userName
        viewHolder.tvReview.text = item.reviewDescription
        createRating(item.rating, viewHolder.llRating)
        viewHolder.itemView.setOnClickListener {

        }
    }

    fun createRating(rating: Int, llRating : LinearLayout) {
        for (i in 0 until rating) {
            var imageView = ImageView(context)
//            var vp = LinearLayout.LayoutParams (LinearLayout.LayoutParams.WRAP_CONTENT,
//            LinearLayout.LayoutParams.WRAP_CONTENT)
            var vp = LinearLayout.LayoutParams(36, 36)
            imageView.layoutParams = vp
            imageView.setPadding(2, 0 , 0, 2)

            imageView.setImageResource(R.drawable.ic_orange_filled_star)
            imageView.setColorFilter(ContextCompat.getColor(context, R.color.white))

            llRating.addView(imageView)
        }
    }

    override fun getCount(): Int {
        return items.size
    }

    inner class CustomSliderReviewVH(itemView: View) : ViewHolder(itemView) {
        var ivPhoto: ImageView = itemView.findViewById(R.id.ivPhoto)
        var tvName: TextView = itemView.findViewById(R.id.tvName)
        var llRating: LinearLayout = itemView.findViewById(R.id.llRating)
        var tvReview: TextView = itemView.findViewById(R.id.tvReview)

    }






}