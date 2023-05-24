package com.rent.app.widget

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.rent.app.util.ext.loadImage
import com.rent.app.util.ext.loadImageCircle

class WidgetBinding {
    companion object {
        @SuppressLint("SetTextI18n")
        @JvmStatic
        @BindingAdapter("loadResource")
        fun loadResource(view: ImageView, param: Int?) {
            param?.let {
                view.setImageDrawable(ContextCompat.getDrawable(view.context, it))
            }
        }

        @SuppressLint("SetTextI18n")
        @JvmStatic
        @BindingAdapter("loadUrlCircle")
        fun loadUrlCircle(view: ImageView, param: String?) {
            param?.let {
                view.loadImageCircle(it)
            }
        }

        @SuppressLint("SetTextI18n")
        @JvmStatic
        @BindingAdapter("loadUrl")
        fun loadUrl(view: ImageView, param: String?) {
            param?.let {
                view.loadImage(it)
            }
        }

        @SuppressLint("SetTextI18n")
        @JvmStatic
        @BindingAdapter("label")
        fun label(view: TextView, param: Int?){
            param?.let {
                view.text = view.context.getString(it)
            }
        }
    }

}