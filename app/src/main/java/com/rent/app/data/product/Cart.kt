package com.rent.app.data.product

import com.rent.app.util.calendar.CalendarUtils

data class Cart(
    var id: Int = 0,
    var startDate: String = CalendarUtils.getCurrentDate(CalendarUtils.SERVER_DATE_FORMAT),
    var endDate: String = CalendarUtils.getCurrentDate(CalendarUtils.SERVER_DATE_FORMAT),
    var qty: Int = 0,
    var product: Product,
    var subTotal : Long = 0,
    var isSelected: Boolean = false,
){
    companion object{
        fun getCarts() : ArrayList<Cart>{
            var carts = ArrayList<Cart>()
            carts.add(Cart(qty = (1..3).random(), product = Product.product()))
            carts.add(Cart(qty = (1..3).random(), product = Product.product()))
            carts.add(Cart(qty = (1..3).random(), product = Product.product()))
            carts.add(Cart(qty = (1..3).random(), product = Product.product()))
            return carts
        }
    }

    fun subTotal() : Long{
        return qty*product.price
    }
}
