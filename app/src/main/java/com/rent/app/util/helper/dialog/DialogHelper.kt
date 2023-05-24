package com.rent.app.util.helper.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.rent.app.R
import com.rent.app.util.ext.hide
import kotlinx.android.synthetic.main.dialog_custom.*

/**
 * [DialogHelper] Class untuk membantu menampilkan popup dialog
 *
 * @property context
 * @property isRequired kondisional untuk menampilkan dialog yang dapat di dismiss
 * dengan klik di luar area / tombol back
 */
open class DialogHelper(val context: Context, var isRequired : Boolean = false){

    var selectionItem = -1
    var dialog: Dialog? = null

    /**
     * builder untuk custom dialog yang akan menampilkan sesuai [resLayout]
     * @param resLayout resource layout sebagai tampilan dari popup dialog
     * @param getView mengembalikan view pada layout untuk di akses ke
     */
    fun dialogCustom(resLayout : Int, state : Int = BottomSheetBehavior.STATE_EXPANDED, getView : (View) -> Unit) : Dialog?{

        val view = LayoutInflater.from(context).inflate(
            resLayout,
            null
        )
        dialog = BottomSheetDialog(context, R.style.BottomSheetDialogTheme)
        dialog?.setContentView(view)
        dialog?.setCancelable(!isRequired)

        if (!isRequired) {
            dialog?.negative?.setOnClickListener {
                dialog?.dismiss()
            }
        }else{
            dialog?.negative?.hide()
        }

        getView(view)

        dialog?.setOnShowListener {
            val bottomSheet = dialog?.findViewById<FrameLayout>(R.id.design_bottom_sheet)
            bottomSheet?.let { it1 -> BottomSheetBehavior.from(it1).state = state }
        }

        val vp = view.parent as View
        vp.setBackgroundColor(Color.TRANSPARENT)

        return dialog
    }


    /**
     * builder untuk custom dialog yang akan menampilkan sesuai [resLayout]
     * @param resLayout resource layout sebagai tampilan dari popup dialog
     * @param getView mengembalikan view pada layout untuk di akses ke
     */
    fun <B : ViewDataBinding>dialogCustomBinding(resLayout : Int, state : Int = BottomSheetBehavior.STATE_EXPANDED, getView : (B) -> Unit) : Dialog?{

        val binding : B = DataBindingUtil.inflate(LayoutInflater.from(context), resLayout, null, false)

        dialog = BottomSheetDialog(context, R.style.BottomSheetDialogTheme)
        dialog?.setContentView(binding.root)
        dialog?.setCancelable(!isRequired)

        if (!isRequired) {
            dialog?.negative?.setOnClickListener {
                dialog?.dismiss()
            }
        }else{
            dialog?.negative?.hide()
        }

        getView(binding)

        dialog?.setOnShowListener {
            val bottomSheet = dialog?.findViewById<FrameLayout>(R.id.design_bottom_sheet)
            bottomSheet?.let { it1 -> BottomSheetBehavior.from(it1).state = state }
        }

        val vp = binding.root
        vp.setBackgroundColor(Color.TRANSPARENT)

        return dialog
    }

    open fun show(){
        dialog?.show()
    }

    fun dismiss(){
        dialog?.dismiss()
    }



}