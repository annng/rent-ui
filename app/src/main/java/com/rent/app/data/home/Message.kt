package com.rent.app.data.home

import com.rent.app.util.calendar.CalendarUtils

data class Message(
    var id : Int = 0,
    var isAdmin : Boolean = false,
    var message : String,
    var date : String = CalendarUtils.getCurrentDate(CalendarUtils.SERVER_DATE_TIME_FORMAT)
){
    companion object{
        fun getMessage() : ArrayList<Message>{
            var messages = ArrayList<Message>()
            messages.add(Message(isAdmin = false, message = "Hi"))
            messages.add(Message(isAdmin = true, message = "Hello, Mr. X. Can I help You?"))
            messages.add(Message(isAdmin = false, message = "Yeah. When is Merchedes one is available?"))
            messages.add(Message(isAdmin = true, message = "It will available 3 days later. Please wait ^_^"))
            messages.add(Message(isAdmin = false, message = "Okay, Thanks"))
            messages.add(Message(isAdmin = true, message = "You\'re welcome"))

            return messages

        }

    }

    fun getSender() : String{
        return if (isAdmin)
            "Admin"
        else
            "Me"
    }
}
