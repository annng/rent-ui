package com.rent.app.data.shipping

class District(
    var id : Int = 0,
    var idCity : Int = 0,
    var value : String
){

    companion object{
        fun getDistricts() : ArrayList<District>{
            val districts = ArrayList<District>()
            districts.add(District(value = "- Select District -"))
            districts.add(District(value = "Ungaran Barat"))
            districts.add(District(value = "Ungaran Timur"))
            districts.add(District(value = "Banyumanik"))
            districts.add(District(value = "Langensari"))
            districts.add(District(value = "Ambarawa"))

            return districts
        }
    }
    override fun toString(): String {
        return value
    }
}