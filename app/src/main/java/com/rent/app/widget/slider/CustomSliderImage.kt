package com.rent.app.widget.slider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.rent.app.R
import com.rent.app.data.home.Banner
import com.rent.app.util.ext.loadImage
import com.rent.app.widget.slider.CustomSliderImage.CustomSliderVH
import com.smarteist.autoimageslider.SliderViewAdapter


class CustomSliderImage(private val items : ArrayList<Banner>) : SliderViewAdapter<CustomSliderVH>() {

    override fun onCreateViewHolder(parent: ViewGroup): CustomSliderVH {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_slider_image, null)
        return CustomSliderVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: CustomSliderVH, position: Int) {
        val item = items[position]
        item.image?.let { viewHolder.ivIllustration.loadImage(it) }
        viewHolder.tvTitle.text = item.title
        viewHolder.itemView.setOnClickListener {

        }
    }

    override fun getCount(): Int {
        return items.size
    }

    inner class CustomSliderVH(itemView: View) : ViewHolder(itemView) {
        var ivIllustration: ImageView = itemView.findViewById(R.id.ivIllustration)
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)

    }
}