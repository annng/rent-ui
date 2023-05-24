package com.rent.app.data.order

import com.rent.app.R
import com.rent.app.util.calendar.CalendarUtils

/**
 * need to be updated
 * when [OrderDetail] isExpired, there is no new data OrderStatus
 */
data class OrderStatus(
    var id : Int = 0,
    var statusName : String = "",
    var information : String = "",
    var createdAt : String = CalendarUtils.getCurrentDate(CalendarUtils.SERVER_DATE_TIME_FORMAT),
    var statusCode : Int = 0
){
    companion object{
        fun getStatuses() : ArrayList<OrderStatus>{
            var statuses = ArrayList<OrderStatus>()
            statuses.add(OrderStatus(statusName = Order.LABEL_WAITING_PAYMENT, information = "Please transfer to bank xxxxxx before 24 Sep 2021 08:00 UTC+8", statusCode = Order.STATUS_WAITING_PAYMENT))
            statuses.add(OrderStatus(statusName = Order.LABEL_PAYMENT_FAILED, information = "You\'re not transfer according time and ask for more time. Please transfer today before 15:00 UTC+8", statusCode = Order.STATUS_PAYMENT_FAILED))
            statuses.add(OrderStatus(statusName = Order.LABEL_PAYMENT_SUCCESS, information = "Your transfer has been received. Please wait us preparing your packaged. We will inform it again.", statusCode = Order.STATUS_PAYMENT_SUCCESS))
            statuses.add(OrderStatus(statusName = Order.LABEL_ON_DELIVERY, information = "Your package is on delivery. Please wait ^_^", statusCode = Order.STATUS_ON_DELIVERY))
            statuses.add(OrderStatus(statusName = Order.LABEL_ARRIVED, information = "Your package has been arrived. Please confirm it to finish orders", statusCode = Order.STATUS_ARRIVED))
            statuses.add(OrderStatus(statusName = Order.LABEL_TRANSACTION_COMPLETE, information = "Thanks for renting our service. We will contact you 1 day before return time.", statusCode = Order.STATUS_TRANSACTION_COMPLETE))

            return statuses
        }
    }

    fun getBackgroundStatus() : Int{
        return when (statusCode) {
            Order.STATUS_WAITING_PAYMENT -> R.color.gray_status
            Order.STATUS_PAYMENT_FAILED -> R.color.red
            Order.STATUS_PAYMENT_SUCCESS -> R.color.green
            Order.STATUS_ON_DELIVERY -> R.color.gray_light
            Order.STATUS_ARRIVED -> R.color.blue
            else -> R.color.green
        }
    }

    fun getTextColorStatus() : Int{
        return when (statusCode) {
            Order.STATUS_WAITING_PAYMENT -> R.color.black_dark
            Order.STATUS_ON_DELIVERY -> R.color.black_dark
            Order.STATUS_PAYMENT_FAILED -> R.color.white
            else -> R.color.white
        }
    }
}
