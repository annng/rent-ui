package com.rent.app.data.order

data class Bank(
    var id: Int = 0,
    var image: String,
    var accountNumber: String,
    var accountName: String
) {
    companion object {
        fun getBanks(): ArrayList<Bank> {
            val banks = ArrayList<Bank>()
            banks.add(
                Bank(
                    image = "https://1.bp.blogspot.com/-zkv5u5OGPEM/VKOWnIRRKBI/AAAAAAAAA7E/ovxa4ZW3I0o/w1200-h630-p-k-no-nu/Logo%2BBank%2BMandiri.png",
                    accountName = "Rent App",
                    accountNumber = "123438xxxxx"
                )
            )
            banks.add(
                Bank(
                    image = "https://logos-download.com/wp-content/uploads/2017/03/BCA_logo_Bank_Central_Asia.png",
                    accountName = "Rent App",
                    accountNumber = "1668332xxxxx"
                )
            )

            return banks
        }
    }
}
