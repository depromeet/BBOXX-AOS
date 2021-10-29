package com.depromeet.bboxx.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.depromeet.bboxx.presentation.extension.isNotBlank
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
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

    private val calendar = Calendar.getInstance()

    fun formatSimplePushTime(time : Date) : String {
        return simplePushFormatter.format(time)
    }


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


    fun simpleDotTimeFormatter(time : String) : String {
        return simpleDotFormatter.format(ZonedDateTime.parse(time).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime())
    }

    fun formatSimpleDateTime(time : String) : String {
        return simpleDateFormatter.format(ZonedDateTime.parse(time).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime())
    }

    fun formatTime(time: String): String{
        return dateFormatter.format(ZonedDateTime.parse(time).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime())
    }

    fun formatSimpleNowTime(time : Date) : String {
        return simpleTimeFormatter.format(time)
    }

    fun formatSimpleFullNowTime(time : Date) : String {
        return simpleFullTimeFormatter.format(time)
    }


    fun formatFullTime(time: String): String{
        return if (time.isNotBlank()) {
            fullDateFormatter.format(ZonedDateTime.parse(time).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime())
        } else {
            ""
        }
    }

    fun formatDepositTime(time : String) : String {
        return fullDateFormatter.format(ZonedDateTime.parse(time).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime().plusHours(6))
    }

    fun formatDepositGlobalTime(time: String): String {
        return fullDateFormatter.format(ZonedDateTime.parse(time).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime().plusHours(24))
    }

    fun formatIsUnlimited(time: String?): Boolean {
        return time.isNullOrEmpty() ||  ZonedDateTime.parse(time).withZoneSameInstant(ZoneId.systemDefault())
            .toLocalDateTime().year < 1970
    }

    fun formatParseToLocalDateTime(time: String) : LocalDateTime {
        return ZonedDateTime.parse(time).withZoneSameInstant(ZoneId.systemDefault())
            .toLocalDateTime()
    }

    fun formatForEffectiveDate(is24Hour: Boolean, time: String): String  {
        val dateTime = ZonedDateTime.parse(time).withZoneSameInstant(ZoneId.systemDefault())
            .toLocalDateTime()

        return if(dateTime.hour == 0 && dateTime.minute == 0) {
            simpleDotFormatter.format(dateTime)
        } else {
            if(is24Hour) {
                fullDateDot24Formatter.format(dateTime)
            } else {
                fullDateDot12Formatter.format(dateTime)
            }

        }
    }

}