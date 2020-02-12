package com.alvayonara.kade_submission_alvayonara

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateTimeConvert {
    fun convertTimeZone(date: String?, time: String?): String? {

        // default format date from API
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

        // default timezone from API (GMT)
        formatter.timeZone = TimeZone.getTimeZone("GMT")

        // assign empty value for convertResult
        var convertResult = ""

        // Use try-catch to prevent null values from date ("dateEvent") and time ("strTime").
        // An error can occur Parse Exception if the value is null so it can't be parsed to Date.
        try {
            val dateTime = "$date $time"

            val dateTimeGMT = formatter.parse(dateTime)

            // reformat date time style (ex: Saturday, 31 December 1997 09:00)
            val formatterReformat = SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm", Locale.getDefault())

            // convert from GMT to GMT+7
            formatterReformat.timeZone = TimeZone.getTimeZone("GMT+7")

            // convert result
            convertResult = formatterReformat.format(dateTimeGMT!!)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return convertResult
    }
}