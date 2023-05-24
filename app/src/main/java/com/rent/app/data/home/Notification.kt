package com.rent.app.data.home

import com.rent.app.data.order.Order
import com.rent.app.data.product.Product
import com.rent.app.util.calendar.CalendarUtils

data class Notification(
    var id: Int = 0,
    var title: String,
    var content: String,
    var photos: String = Product.getProductImages()[(0..2).random()],
    var createdAt: String = CalendarUtils.getCurrentDate(CalendarUtils.SERVER_DATE_TIME_FORMAT),
    var type: String,
    var order: Order? = null,
) {
    companion object {
        const val TYPE_NOTIFICATION = "notification"
        const val TYPE_ORDER = "order"

        fun getNotifications(): ArrayList<Notification> {
            var notifications = ArrayList<Notification>()
            notifications.add(
                Notification(
                    title = "New Promo",
                    content = "Check promo page and enjoy our discount",
                    type = TYPE_NOTIFICATION
                )
            )

            val order1 = Order.getOrder()
            val order2 = Order.getOrder()
            notifications.add(
                Notification(
                    title = "${order1.noOrder} has been update",
                    content = "Your order on ${order1.status} now",
                    type = TYPE_ORDER,
                    order = order1
                )
            )
            notifications.add(
                Notification(
                    title = "${order2.noOrder} has been update",
                    content = "Your order on ${order2.status} now",
                    type = TYPE_ORDER,
                    order = order2
                )
            )

            notifications.add(
                Notification(
                    title = "New Promo",
                    content = "Check promo page and enjoy our discount",
                    type = TYPE_NOTIFICATION
                )
            )

            return notifications
        }
    }

    fun isOrder(): Boolean {
        return type == TYPE_ORDER
    }
}