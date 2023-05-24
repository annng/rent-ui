package com.rent.app.util.helper.dialog

import android.content.Context
import com.rent.app.R
import com.rent.app.util.ext.initItem
import kotlinx.android.synthetic.main.dialog_list.view.*

class DialogList(val context : Context){

    fun show(title : String, items : ArrayList<*>, onClick : (Int) -> Unit){
        val dialogList = DialogHelper(context)
        dialogList.dialogCustom(R.layout.dialog_list){ view ->
            view.tvTitle.text = title
            val adapter = DialogListAdapter(items){
                onClick(it)
                dialogList.dismiss()
            }

            view.rvList.initItem(context, adapter)
        }

        dialogList.show()
    }
}