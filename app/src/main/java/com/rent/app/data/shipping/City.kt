package com.rent.app.data.shipping

data class City(
    var id : Int = 0,
    var value : String
){
    override fun toString(): String {
        return value
    }

    companion object{
        fun getCities() : ArrayList<City>{
            val cities = ArrayList<City>()
            cities.add(City(value = "- Select City -"))
            cities.add(City(value = "Bandung"))
            cities.add(City(value = "Jakarta"))
            cities.add(City(value = "Semarang"))
            cities.add(City(value = "Surabaya"))

            return cities
        }
    }
}
