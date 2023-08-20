package ru.vaa.vtask.components

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    /**
     * @param date - which we want 3 letters abbreviation from
     * @return day abbreviation, for example Fri, Thu, Mon, etc...
     */
    fun getDay3LettersName(date: Date): String =
        SimpleDateFormat("EE", Locale.getDefault()).format(date)

    /**
     * @param date - which we want month name from
     * @return month name, for example December, September, January, etc...
     */
    fun getMonthName(date: Date): String =
        SimpleDateFormat("MMMM", Locale.getDefault()).format(date)

    /**
     * @param date - which we want year from
     * @return year, for example 2010, 2019, 2020, 2034...
     */
    fun getYear(date: Date): String =
        SimpleDateFormat("yyyy", Locale.getDefault()).format(date)

    /**
     * @param date - which we want day number from
     * @return number of day in month, for example 15, 16, 17, etc...
     */
    fun getDayNumber(date: Date): String =
        SimpleDateFormat("d", Locale.getDefault()).format(date)

    /**
     * @param count - future days count from now which we want to load
     * @return list of future dates with specified length
     */
    fun getFutureDates(count: Int,startCalendar: Calendar = Calendar.getInstance(Locale.getDefault()),includeStart :Boolean= true): MutableList<Date> {
        val futureDateList = mutableListOf<Date>()
        if (includeStart)
            futureDateList.add(startCalendar.time)
        for (i in 0 until count) {
            startCalendar.add(Calendar.DATE, 1)
            futureDateList.add(startCalendar.time)
        }
        return futureDateList
    }
}