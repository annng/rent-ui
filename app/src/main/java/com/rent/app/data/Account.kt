package com.rent.app.data

import com.rent.app.util.Constants

data class Account(
    var id : Int = 0,
    var name : String,
    var email : String,
    var phone : String,
    var address : String,
    var gender : String,
    var photos : String,
    var birthday : String
){
    companion object{
        fun getAccount() : Account{
            return Account(name = "John Doe", email = "JohnDoe@email.com", phone = "(+62) 857415xxxx",
            address = "Boulevard 5 Cliff Hill, Ungaran, Semarang", gender = "Male", birthday = "24 Desember 1996", photos = Constants.SAMPLE.IMG_PROFILE_FEMALE)
        }
    }

}