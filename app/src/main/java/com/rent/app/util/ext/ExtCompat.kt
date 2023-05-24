package com.rent.app.util.ext

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import com.rent.app.R

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun Drawable.setColorDrawable(context: Context, newColor: Int) {
    this.mutate().setColorFilter(ContextCompat.getColor(context, newColor), PorterDuff.Mode.SRC_IN)
}

fun Drawable.setColorDrawableParse(context: Context, newColor: Int) {
    this.mutate().setColorFilter(newColor, PorterDuff.Mode.SRC_IN)
}

fun SearchView.init(hint: String = "Cari", onChanged: (String) -> Unit, onSubmit: (String) -> Unit) {
    this.queryHint = hint
    this.maxWidth = android.R.attr.width

    (this.findViewById(R.id.search_src_text) as EditText)
        .setHintTextColor(resources.getColor(R.color.hint_label))
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            onSubmit(query)
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            onChanged(newText)

            return true
        }
    })

}