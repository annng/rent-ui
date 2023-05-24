package com.rent.app.ui.main.child.cart

import androidx.lifecycle.MutableLiveData
import com.rent.app.base.BaseViewModel
import com.rent.app.data.product.Cart
import javax.inject.Inject
class CartViewModel @Inject constructor():  BaseViewModel()  {
    val obsSelectedCart = MutableLiveData<List<Cart>>()

    fun getSelectedCart(carts : List<Cart>){
        var arrCart = ArrayList<Cart>()
        for (i in carts){
            if (i.isSelected)
                arrCart.add(i)
        }

        obsSelectedCart.postValue(arrCart)
    }
}