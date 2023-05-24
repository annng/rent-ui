package com.rent.app.data.home

import com.rent.app.util.Constants
import com.rent.app.util.calendar.CalendarUtils

data class Banner(
    var id: Int = 0,
    var title: String,
    var image: String? = null,
    var description: String? = null,
    var created_at: String? = CalendarUtils.getCurrentDate(
        CalendarUtils.SERVER_DATE_FORMAT
    )
) {

    companion object {
        fun getBanners(): ArrayList<Banner> {
            var banners = ArrayList<Banner>()
            banners.add(
                Banner(
                    title = "${Constants.LOREM.TITLE} 1",
                    image = Constants.SAMPLE.IMG_BANNER_1,
                    description = Constants.LOREM.DESCRIPTION
                )
            )
            banners.add(
                Banner(
                    title = "${Constants.LOREM.TITLE} 2",
                    image = Constants.SAMPLE.IMG_BANNER_2,
                    description = Constants.LOREM.DESCRIPTION
                )
            )
            banners.add(
                Banner(
                    title = "${Constants.LOREM.TITLE} 3",
                    image = Constants.SAMPLE.IMG_BANNER_3,
                    description = Constants.LOREM.DESCRIPTION
                )
            )

            return banners
        }
    }
}