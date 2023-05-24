package com.rent.app.util.calendar

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

open class CalendarUtils {
    companion object {
        fun getCurrentDate(format: String): String {
            val simpleDateFormatter = SimpleDateFormatter()
            val sdf = SimpleDateFormat(format)

            return sdf.format(Date())
        }

        fun setFormatDate(OldDate: String, Oldformat: String, newFormat: String): String {
            var format = SimpleDateFormat(Oldformat, Locale.getDefault())
            var newDate: Date? = null
            try {
                newDate = format.parse(OldDate)
            } catch (e: ParseException) {
                e.printStackTrace()
                newDate = null
                return "-"
            } catch (e: NullPointerException) {
                e.printStackTrace()
                newDate = null
                return "-"
            }

            format = SimpleDateFormat(newFormat, Locale("in", "ID", "ID"))

            return format.format(newDate)
        }

        fun sumRangeDate(strStartDate: String?, strEndDate: String?): Int {
            val format = SimpleDateFormat("yyyy-MM-dd")
            try {
                val startDate = format.parse(strStartDate)
                val endDate = format.parse(strEndDate)
                val rangeInMili = endDate.time - startDate.time
                return TimeUnit.MILLISECONDS.toDays(rangeInMili).toInt()
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return 0
        }


        const val SERVER_DATE_FORMAT = "yyyy-MM-dd"
        const val SERVER_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"
        const val PICKER_DATE_FORMAT = "yyyy-MM-d"
        const val VIEW_DATE_FORMAT = "dd MMMM yyyy"
        const val VIEW_DATE_SHORT_FORMAT = "dd MMM yyyy"
        const val VIEW_DATE_PERIOD_FORMAT = "dd MMM"
        const val VIEW_DATE_TIME_FORMAT = "dd MMM yyyy HH:mm"
        const val STICKY_ID_FORMAT = "yyyyMMdd"
        const val VIEW_MONTH_FORMAT = "MMMM yyyy"
        const val FILTER_MONTH_FORMAT = "yyyy-MM"

    }
}
