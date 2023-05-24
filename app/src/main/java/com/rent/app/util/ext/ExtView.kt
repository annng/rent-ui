package com.rent.app.util.ext

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.*
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatSpinner
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.crocodic.core.helper.Log
import com.rent.app.R
import java.io.File
import java.util.*

/**
 * Melakukan aksi pada field [EditText] ketika terdapat perubahan value dan mengembalikan value
 * dengan tipe data [String]
 * @param EditText field yang akan di ambil valuenya
 * @param onChanged aksi yang dijalankan ketika value dari [EditText] berubah dan diteruskan ke parent class
 */
fun EditText.onChangeText(onChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            onChanged.invoke(p0.toString())
        }

        override fun afterTextChanged(editable: Editable?) {

        }
    })
}

/**
 * Menjalankan aksi [onSearch] ketika tombol search di klik pada field [EditText]
 * dengan mengembalikan value [String]
 * @param
 */
infix fun EditText.onSubmitSearch(onSearch: (String) -> Unit) {
    this.setOnEditorActionListener { textView, actionId, event ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_NEXT
                || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_GO) {
            onSearch(this.text.toString())
            this.clearFocus()
            true
        }
        false
    }
}

fun EditText.afterChangeText(onChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            onChanged(editable.toString())
        }
    })
}



fun AppCompatSpinner.init(context: Context, items: ArrayList<*>): ArrayAdapter<*> {
    val myAdapter = object : ArrayAdapter<Any>(context, R.layout.item_spinner, items) {
        override fun isEnabled(position: Int): Boolean {
            return true
        }

        override fun getDropDownView(
            position: Int, convertView: View?,
            parent: ViewGroup
        ): View {
            val view = super.getDropDownView(position, convertView, parent)
            val tv = view as TextView

            tv.setTextColor(Color.parseColor("#4B4A4F"))

            return view
        }
    }

    myAdapter.setDropDownViewResource(R.layout.item_spinner)

    this.adapter = myAdapter

    return myAdapter
}

fun AppCompatSpinner.init(context: Context, items: Array<*>): ArrayAdapter<*> {
    val myAdapter = object : ArrayAdapter<Any>(context, R.layout.item_spinner, items) {
        override fun isEnabled(position: Int): Boolean {
            return true
        }

        override fun getDropDownView(
            position: Int, convertView: View?,
            parent: ViewGroup
        ): View {
            val view = super.getDropDownView(position, convertView, parent)
            val tv = view as TextView

            tv.setTextColor(Color.parseColor("#4B4A4F"))

            return view
        }
    }

    myAdapter.setDropDownViewResource(R.layout.item_spinner)

    this.adapter = myAdapter

    return myAdapter
}


fun AppCompatSpinner.initWithHint(context: Context, items: ArrayList<*>): ArrayAdapter<*> {
    val myAdapter = object : ArrayAdapter<Any>(context, R.layout.item_spinner, items) {
        override fun isEnabled(position: Int): Boolean {
            return position != 0
        }

        override fun getDropDownView(
            position: Int, convertView: View?,
            parent: ViewGroup
        ): View {
            val view = super.getDropDownView(position, convertView, parent)
            val tv = view as TextView
            if (position == 0) {
                // Set the disable item text color
                tv.setTextColor(Color.parseColor("#B1B1B0"))
            } else {
                tv.setTextColor(Color.parseColor("#4B4A4F"))
            }
            return view
        }
    }

    myAdapter.setDropDownViewResource(R.layout.item_spinner)

    this.adapter = myAdapter

    return myAdapter
}

fun RecyclerView.initItem(activity: Context?, adapter: RecyclerView.Adapter<*>) {
    val mLayoutManager = LinearLayoutManager(activity)
    this.layoutManager = mLayoutManager
    this.setHasFixedSize(false)
    this.setItemViewCacheSize(20)
    this.itemAnimator = DefaultItemAnimator()
    this.adapter = adapter

}

fun RecyclerView.initItemHorizontal(activity: Context, adapter: RecyclerView.Adapter<*>) {
    val mLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
    this.layoutManager = mLayoutManager
    this.setHasFixedSize(false)
    this.setItemViewCacheSize(20)
    this.itemAnimator = DefaultItemAnimator()
    this.adapter = adapter

}

fun RecyclerView.initItemGrid(activity: Context, adapter: RecyclerView.Adapter<*>, gridCount: Int) {
    val mLayoutManager = GridLayoutManager(activity, gridCount)
    this.layoutManager = mLayoutManager
    this.setHasFixedSize(false)
    this.setItemViewCacheSize(20)
    this.itemAnimator = DefaultItemAnimator()
    this.adapter = adapter

}

fun RecyclerView.onEndScroll(isGrid: Boolean = false, listener: () -> Unit, isDown : (Boolean) -> Unit) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            var mLayoutManager: LinearLayoutManager

            if (isGrid)
                mLayoutManager = recyclerView.layoutManager as GridLayoutManager
            else
                mLayoutManager = recyclerView.layoutManager as LinearLayoutManager


            val visibleItemCount = recyclerView.childCount
            val totalItemCount = mLayoutManager.itemCount
            val firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition()


            if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
                listener()
            }

            if (dy > 0) {
                isDown(true)
            } else {
                isDown(false)
            }
        }
    })
}

infix fun ImageView.loadImage(strUrl: String) {
    try {
//        Glide.with(this).load(strUrl).into(this)
        Log.e("Glide terpanggil")
        Glide.with(this)
            .load(strUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.e("Glide ${e?.localizedMessage}")
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.e("Glide finish")
                    return false
                }

            })
            .into(this)
    } catch (e: Exception) {

    }

}


infix fun ImageView.loadImage(file: File) {
    try {
        Glide.with(this).load(file).into(this)
    } catch (e: Exception) {

    }

}

infix fun ImageView.loadImageRounded(strUrl: String) {
    try {
        Glide.with(this)
            .load(strUrl)
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(16)))
            .into(this)
    } catch (e: Exception) {

    }

}

infix fun ImageView.loadImageCenterCrop(strUrl: String) {
    try {
        Glide.with(this).load(strUrl)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    } catch (e: Exception) {

    }

}

infix fun ImageView.loadImageCircle(strUrl: String) {
    try {
        Glide.with(this).load(strUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(this)
    } catch (e: Exception) {

    }
}


infix fun ImageView.loadImageCircle(bitmap: Bitmap) {
    try {
        Glide.with(this).load(bitmap)
            .apply(RequestOptions.circleCropTransform())
            .into(this)
    } catch (e: Exception) {

    }
}

fun TextView.spannedText(spannedString: String, color: Int, onClick: () -> Unit) {
    var mySpannable = SpannableString(this.text)
    mySpannable.setSpan(ForegroundColorSpan(color), 0, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    mySpannable.setSpan(UnderlineSpan(), 0, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

    val myClickableSpan = object : ClickableSpan() {
        override fun onClick(widget: View) {
            onClick
        }
    }

    mySpannable.setSpan(myClickableSpan, 0, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    this.text = mySpannable

}


