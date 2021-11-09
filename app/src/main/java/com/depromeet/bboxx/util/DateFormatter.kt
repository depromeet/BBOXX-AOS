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
    private val simpleTimeFormatter = SimpleDateFormat("yyyy년 MM월 dd일")
    private val simpleFullTimeFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
    private val fullDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.getDefault())
    private val simpleDotFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd", Locale.getDefault())
    private val fullDateDot12Formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd a hh:mm", Locale.getDefault())
    private val fullDateDot24Formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm", Locale.getDefault())
    private val growthTimeFormatter = SimpleDateFormat("MM. dd EE")

    private val monthTimeFormatter = SimpleDateFormat("MM")
    private val yearTimeFormatter = SimpleDateFormat("yyyy")
    private val calendarTimeFormatter = SimpleDateFormat("yyyy년 MM월")
    private val calendar = Calendar.getInstance()


    fun formatFormatterCalc(date : String) : String {
        try{
            val mDate: Date = simpleTimeFormatter.parse(date)
            calendar.time = mDate
            calendar.add(Calendar.DATE, -1)

            return SimpleDateFormat("MM.dd", Locale.getDefault()).format(calendar.time)
        }
        catch (e: Exception){
            e.printStackTrace()
        }
        return ""
    }

    fun formatNowTime() : String {
        return simpleTimeFormatter.format(Date())
//        simpleTimeFormatter.timeZone = TimeZone.getTimeZone("Asia/Seoul")
//        return simpleTimeFormatter.format(Calendar.getInstance().time)
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
}