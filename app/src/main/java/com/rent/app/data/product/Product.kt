package com.rent.app.data.product

import com.rent.app.util.Constants

data class Product(
    var id : Int = 0,
    var productName : String,
    var thumbnail : String,
    var description : String = Constants.LOREM.DESCRIPTION,
    var spesification : String = Constants.LOREM.DESCRIPTION,
    var price : Long = 0,
    var stock : Int = 0,
    var isFavorite : Boolean = false,
    var productImages : ArrayList<String> = ArrayList(),
    var coverageArea : ArrayList<String> = ArrayList()
){
    companion object{
        fun products(isGood: Boolean = true) : ArrayList<Product>{
            var products = ArrayList<Product>()
            if (isGood) {
                products.add(
                    Product(
                        productName = "Sport Car",
                        thumbnail = Constants.SAMPLE.IMG_CAR_1,
                        price = 10000000,
                        stock = 3,
                        productImages = getProductImages(),
                        coverageArea = getCoverageAreas(),
                        isFavorite = true
                    )
                )
                products.add(
                    Product(
                        productName = "Merchedes",
                        thumbnail = Constants.SAMPLE.IMG_CAR_2,
                        price = 12000000,
                        stock = 2,
                        productImages = getProductImages(),
                        coverageArea = getCoverageAreas()
                    )
                )
                products.add(
                    Product(
                        productName = "Toyota",
                        thumbnail = Constants.SAMPLE.IMG_CAR_3,
                        price = 15000000,
                        stock = 2,
                        productImages = getProductImages(),
                        coverageArea = getCoverageAreas(),
                        isFavorite = true
                    )
                )
            }else{
                products.add(
                    Product(
                        productName = "Salesman",
                        thumbnail = Constants.SAMPLE.IMG_SALES_1,
                        price = 150000,
                        stock = 5,
                        productImages = getProductImages(),
                        coverageArea = getCoverageAreas()
                    )
                )
                products.add(
                    Product(
                        productName = "Salesgirl",
                        thumbnail = Constants.SAMPLE.IMG_SALES_2,
                        price = 200000,
                        stock = 7,
                        productImages = getProductImages(),
                        coverageArea = getCoverageAreas(),
                        isFavorite = true
                    )
                )
                products.add(
                    Product(
                        productName = "Repairman",
                        thumbnail = Constants.SAMPLE.IMG_SALES_3,
                        price = 120000,
                        stock = 4,
                        productImages = getProductImages(),
                        coverageArea = getCoverageAreas()
                    )
                )
            }

            return products
        }

        fun product(isGood: Boolean = true) : Product{
            var products = products(isGood)
            return products[(0 until products.size).random()]
        }

        fun getProductImages(isGood : Boolean = true) : ArrayList<String>{
            val images = ArrayList<String>()
            if (isGood) {
                images.add(Constants.SAMPLE.IMG_CAR_1)
                images.add(Constants.SAMPLE.IMG_CAR_2)
                images.add(Constants.SAMPLE.IMG_CAR_3)
            }else{
                images.add(Constants.SAMPLE.IMG_VACUUM_1)
                images.add(Constants.SAMPLE.IMG_VACUUM_2)
                images.add(Constants.SAMPLE.IMG_VACUUM_3)
            }

            return images
        }

        fun getCoverageAreas() : ArrayList<String>{
            var areas = ArrayList<String>()
            areas.add("Indonesia")
            areas.add("Japan")
            areas.add("USA")

            return areas
        }
    }
}
