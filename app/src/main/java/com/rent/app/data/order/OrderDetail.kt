package com.rent.app.data.order

import com.rent.app.util.calendar.CalendarUtils

data class OrderDetail(
    var id: Int = 0,
    var noOrder: String,
    var subTotal: Long = 0,
    var discountAmount: Long = 0,
    var deliveryCost: Long = 0,
    var total: Long = 0,
    var isReviewed: Boolean = false,
    var isPaid: Boolean = false,
    var isExpired: Boolean = false,
    var paymentExpired: String = CalendarUtils.getCurrentDate(CalendarUtils.SERVER_DATE_TIME_FORMAT),
    var statuses: ArrayList<OrderStatus>,
    var products: ArrayList<OrderProduct>,
    var delivery: OrderDelivery
){
    companion object{
        fun getOrderDetail() : OrderDetail{
            return OrderDetail(
                noOrder = "ORDER3412xxxx",
                subTotal = 10000000,
                discountAmount = 200000,
                deliveryCost = 0,
                total = 9800000,
                isReviewed = Math.random() < 0.5,
                isPaid = Math.random() < 0.5,
                isExpired = Math.random() < 0.5,
                statuses = OrderStatus.getStatuses(),
                products = OrderProduct.getProducts(),
                delivery = OrderDelivery.getOrderDelivery()
            )
        }


    }
}
