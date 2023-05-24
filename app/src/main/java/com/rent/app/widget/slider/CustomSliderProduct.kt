package com.rent.app.widget.slider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.rent.app.R
import com.rent.app.util.ext.hide
import com.rent.app.util.ext.loadImage
import com.smarteist.autoimageslider.SliderViewAdapter


class CustomSliderProduct(private val items : ArrayList<String>) : SliderViewAdapter<CustomSliderProduct.CustomSliderProductVH>() {

    override fun onCreateViewHolder(parent: ViewGroup): CustomSliderProductVH {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_slider_image, null)
        return CustomSliderProductVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: CustomSliderProductVH, position: Int) {
        val item = items[position]
        viewHolder.ivIllustration.loadImage(item)
        viewHolder.tvTitle.hide()
        viewHolder.itemView.setOnClickListener {

        }
    }

    override fun getCount(): Int {
        return items.size
    }

    inner class CustomSliderProductVH(itemView: View) : ViewHolder(itemView) {
        var ivIllustration: ImageView = itemView.findViewById(R.id.ivIllustration)
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)

    }
}