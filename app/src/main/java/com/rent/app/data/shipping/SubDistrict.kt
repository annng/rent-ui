package com.rent.app.data.shipping

data class SubDistrict(
    var id : Int = 0,
    var idDistrict : Int = 0,
    var value : String
){

    companion object{
        fun getSubDistrict() : ArrayList<SubDistrict>{
            val subDistricts = ArrayList<SubDistrict>()
            subDistricts.add(SubDistrict(value = "- Select Sub-District -"))
            subDistricts.add(SubDistrict(value = "Kalongan"))
            subDistricts.add(SubDistrict(value = "Pudah Payung"))
            subDistricts.add(SubDistrict(value = "Jatingaleh"))
            subDistricts.add(SubDistrict(value = "Cipete"))

            return subDistricts
        }
    }
    override fun toString(): String {
        return value
    }
}
