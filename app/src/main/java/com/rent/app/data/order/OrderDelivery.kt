package com.rent.app.data.order

data class OrderDelivery(
    var name: String,
    var phone: String,
    var address: String,
    var city: String,
    var district: String,
    var subDistrict: String
) {
    companion object {
        fun getOrderDelivery(): OrderDelivery {
            return OrderDelivery(
                "John Doe",
                "(+62) 8575353xxxx",
                "Street 20 Boulevard",
                "Semarang",
                "Ungaran",
                "Boulevard Hill"
            )
        }
    }
}
