package com.depromeet.bboxx.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class DateFormatter{

    private var simplePushFormatter =   SimpleDateFormat("MM.dd")
    private var simpleDateFormatter = DateTimeFormatter.ofPattern("MM.dd", Locale.getDefault())
    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())
    private val simpleTimeFormatterCal = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    private val simpleFullTimeFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
    private val growthTimeFormatter = SimpleDateFormat("MM. dd EE")
    private val simpleTimeFormatter = SimpleDateFormat("yyyy년 MM월 dd일")
    private val monthTimeFormatter = SimpleDateFormat("MM")
    private val yearTimeFormatter = SimpleDateFormat("yyyy")
    private val calendarTimeFormatter = SimpleDateFormat("yyyy년 MM월")
    private val calendar = Calendar.getInstance()

    fun formatFormatterCalc(date : String) : String {
        try{
            val mDate: Date = simpleTimeFormatterCal.parse(date)
            calendar.time = mDate

            return SimpleDateFormat("MM.dd", Locale.getDefault()).format(calendar.time)
        }
        catch (e: Exception){
            e.printStackTrace()
        }
        return ""
    }

    fun formatFormatterEmotion(date : String) : String {
        try{
            val mDate: Date = simpleTimeFormatterCal.parse(date)
            calendar.time = mDate

            return SimpleDateFormat("MM. dd. EE", Locale.getDefault()).format(calendar.time)
        }
        catch (e: Exception){
            e.printStackTrace()
        }
        return ""
    }

    fun formatNowTime() : String {
        return simpleTimeFormatter.format(Date())
    }

    fun growthNowTime(): String{
        return growthTimeFormatter.format(Date())
    }

    fun formatNowMonth(): String{
        return monthTimeFormatter.format(Date())
    }

    fun formatNowYear(): String{
        return yearTimeFormatter.format(Date())
    }

    fun calendarNowTime(): String{
        return calendarTimeFormatter.format(Date())
    }

    fun growthCalendarAddTime(date: String): String{
        try{
            val mDate: Date = calendarTimeFormatter.parse(date)
            calendar.time = mDate
            calendar.add(Calendar.MONTH, +1)

            return SimpleDateFormat("yyyy년 MM월", Locale.getDefault()).format(calendar.time)
        }
        catch (e: Exception){
            e.printStackTrace()
        }
        return ""
    }

    fun growthCalendarMinerTime(date: String): String{
        try{
            val mDate: Date = calendarTimeFormatter.parse(date)
            calendar.time = mDate
            calendar.add(Calendar.MONTH, -1)

            return SimpleDateFormat("yyyy년 MM월", Locale.getDefault()).format(calendar.time)
        }
        catch (e: Exception){
            e.printStackTrace()
        }
        return ""
    }

    fun calendarAddYear(date: String): String{
        try{
            val mDate: Date = calendarTimeFormatter.parse(date)
            calendar.time = mDate
            calendar.add(Calendar.YEAR, +1)

            return SimpleDateFormat("yyyy년 MM월", Locale.getDefault()).format(calendar.time)
        }
        catch (e: Exception){
            e.printStackTrace()
        }
        return ""
    }


    fun calendarMinerYear(date: String): String{
        try{
            val mDate: Date = calendarTimeFormatter.parse(date)
            calendar.time = mDate
            calendar.add(Calendar.YEAR, -1)

            return SimpleDateFormat("yyyy년 MM월", Locale.getDefault()).format(calendar.time)
        }
        catch (e: Exception){
            e.printStackTrace()
        }
        return ""
    }


    private object TIME_MAXIMUM {
        const val SEC = 60
        const val MIN = 60
        const val HOUR = 24
        const val DAY = 30
        const val MONTH = 12
    }

    fun convertDateBefore(date: String):String{
        try{
            val df = simpleTimeFormatterCal.parse(date)

            return formatTimeString(df.time)?:""
        }
        catch (e: Exception){
            e.printStackTrace()
        }
        return ""
    }

    fun formatTimeString(regTime: Long): String? {
        val curTime = System.currentTimeMillis()
        var diffTime = (curTime - regTime) / 1000
        var msg: String? = null
        if (diffTime < TIME_MAXIMUM.SEC) {
            msg = "방금 전"
        } else if (TIME_MAXIMUM.SEC.let { diffTime /= it; diffTime } < TIME_MAXIMUM.MIN) {
            msg = diffTime.toString() + "분 전"
        } else if (TIME_MAXIMUM.MIN.let { diffTime /= it; diffTime } < TIME_MAXIMUM.HOUR) {
            msg = diffTime.toString() + "시간 전"
        } else if (TIME_MAXIMUM.HOUR.let { diffTime /= it; diffTime } < TIME_MAXIMUM.DAY) {
            msg = diffTime.toString() + "일 전"
        } else if (TIME_MAXIMUM.DAY.let { diffTime /= it; diffTime } < TIME_MAXIMUM.MONTH) {
            msg = diffTime.toString() + "달 전"
        } else {
            msg = diffTime.toString() + "년 전"
        }
        return msg
    }
}