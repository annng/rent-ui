package com.crocodic.core.helper


import android.content.Context
import android.widget.Toast
import org.json.JSONArray


class ToastHelper constructor(val context: Context) {


    private lateinit var toast: Toast

    fun showAToast(st: String?, duration: Int) {
        try {
            toast.view?.isShown
            toast.setText(st)
        } catch (e: Exception) {
            toast = Toast.makeText(context, st, duration)
        }

        toast.show()
    }

    fun showAToast(st: String?) {
        try {
            toast.view?.isShown
            toast.setText(st)
        } catch (e: Exception) {
            toast = Toast.makeText(context, st, Toast.LENGTH_SHORT)
        }

        toast.show()
    }

    private fun showToasts(items: JSONArray) {
        for (i in 0 until items.length()) {
            Toast.makeText(context, items[i].toString(), Toast.LENGTH_SHORT).show()
        }
    }

    /*fun showJsonToasts(item: ApiResponse) {
        if (item.stateView.status == State.ERROR) {
            if (!item.message.isNull(0)) {
                showToasts(item.message)
            } else {
                showAToast(item.throwableBody)
            }
        } else if (item.stateView.status == State.WRONG) showAToast(item.message)
    }*/

    fun cancelToast() {
        if (toast.view!!.isShown) {
            toast.cancel()
        }
    }

}