package com.rent.app.ui.product.detail

import androidx.lifecycle.MutableLiveData
import com.rent.app.base.BaseViewModel
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor() : BaseViewModel() {
    val obsUpdateQty = MutableLiveData<Int>()

    fun isStockAvailable(qty: Int, stock: Int): Boolean {
        return qty < stock
    }

    fun plusQty(qty: Int, stock: Int) {
        if (isStockAvailable(qty, stock)) {
            var strQty = qty.toString()
            val intQty = Integer.parseInt(strQty) + 1
            obsUpdateQty.postValue(intQty)
        }
    }

    fun minQty(qty : Int){
        var strQty = qty.toString()

        if (strQty.isEmpty() || qty == 0) {
            strQty = "0"
            obsUpdateQty.postValue(strQty.toInt())
        }else{
            obsUpdateQty.postValue(Integer.parseInt(strQty) - 1)
        }
    }
}