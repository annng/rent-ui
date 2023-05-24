package com.rent.app.data.home

import com.rent.app.util.Constants
import com.rent.app.util.calendar.CalendarUtils

data class Review(
    var id : Int = 0,
    var userId : Int = 0,
    var userName : String = "",
    var userEmail : String = "",
    var userPhoto : String = "",
    var createdAt : String = CalendarUtils.getCurrentDate(
        CalendarUtils.SERVER_DATE_FORMAT),
    var rating : Int = (0..5).random(),
    var reviewDescription : String = Constants.LOREM.REVIEW
){
    companion object{
        fun getReviews() : ArrayList<Review>{
            val reviews = ArrayList<Review>()
            reviews.add(Review(userName = "John Doe", userEmail = "johndoe@email.com", userPhoto = Constants.SAMPLE.IMG_PROFILE_MALE))
            reviews.add(Review(userName = "Jane Doe", userEmail = "janedoe@email.com", userPhoto = Constants.SAMPLE.IMG_PROFILE_FEMALE))
            return reviews
        }
    }
}
