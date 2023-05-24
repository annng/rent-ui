package com.rent.app.data.order

import com.rent.app.R
import com.rent.app.util.calendar.CalendarUtils

data class Order(
    var id :Int = 0,
    var status : String,
    var noOrder : String,
    var invoiceDate : String = CalendarUtils.getCurrentDate(CalendarUtils.SERVER_DATE_TIME_FORMAT),
    var rentDate : String = "14 Sep 2021 - 18 Sep 2021",
    var statusCode : Int
){
    companion object{
        const val STATUS_WAITING_PAYMENT = 1
        const val STATUS_PAYMENT_FAILED = 2
        const val STATUS_PAYMENT_SUCCESS = 3
        const val STATUS_ON_DELIVERY = 4
        const val STATUS_ARRIVED = 5
        const val STATUS_TRANSACTION_COMPLETE = 6

        const val LABEL_WAITING_PAYMENT = "Waiting Payment"
        const val LABEL_PAYMENT_FAILED = "Payment Failed"
        const val LABEL_PAYMENT_SUCCESS = "Payment Success"
        const val LABEL_ON_DELIVERY = "On Delivery"
        const val LABEL_ARRIVED = "Arrived"
        const val LABEL_TRANSACTION_COMPLETE = "Transaction Complete"

        fun getOrders() : ArrayList<Order>{
            var orders = ArrayList<Order>()
            orders.add(Order(status = LABEL_WAITING_PAYMENT, noOrder = "ORDERXXX291", statusCode = STATUS_WAITING_PAYMENT))
            orders.add(Order(status = LABEL_ARRIVED, noOrder = "ORDERXXX225", statusCode = STATUS_ARRIVED))
            orders.add(Order(status = LABEL_ON_DELIVERY, noOrder = "ORDERXXX245", statusCode = STATUS_ON_DELIVERY))
            orders.add(Order(status = LABEL_TRANSACTION_COMPLETE, noOrder = "ORDERXXX284", statusCode = STATUS_TRANSACTION_COMPLETE))

            return orders
        }


        fun getOrder() : Order{
            return getOrders()[(0..3).random()]
        }

    }

    fun getBackgroundStatus() : Int{
        return when (statusCode) {
            STATUS_WAITING_PAYMENT -> R.color.gray_status
            STATUS_PAYMENT_FAILED -> R.color.orange
            STATUS_PAYMENT_SUCCESS -> R.color.yellow_dark
            STATUS_ON_DELIVERY -> R.color.orange
            STATUS_ARRIVED -> R.color.blue
            else -> R.color.green
        }
    }

    fun getTextColorStatus() : Int{
        return when (statusCode) {
            STATUS_WAITING_PAYMENT -> R.color.black_dark
            STATUS_PAYMENT_FAILED -> R.color.white
            else -> R.color.white
        }
    }
}
