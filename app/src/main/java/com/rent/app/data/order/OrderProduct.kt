package com.rent.app.data.order

import com.rent.app.util.Constants

data class OrderProduct(
    var id : Int = 0,
    var name : String = "",
    var image : String = "",
    var qty : Int = 0,
    var subTotal : Long = 0,
    var periodeRent : String = "21 Sep 2021 - 25 Sep 2021"
){
    companion object{
        fun getProducts() : ArrayList<OrderProduct>{
            var products = ArrayList<OrderProduct>()
            products.add(OrderProduct(name = "Merchedes", image = Constants.SAMPLE.IMG_CAR_1, qty = 1, subTotal = 4000000*1))
            products.add(OrderProduct(name = "Repairman", image = Constants.SAMPLE.IMG_SALES_3, qty = 2, subTotal = 2000000*2))
            products.add(OrderProduct(name = "BMW", image = Constants.SAMPLE.IMG_CAR_2, qty = 1, subTotal = 2000000*1))
            return products
        }
    }
}
