package com.depromeet.bboxx.util

import android.content.res.Resources
import android.util.DisplayMetrics

object DisplayUtils {

    private fun getDisplayMetrics(): DisplayMetrics {
        return Resources.getSystem().displayMetrics
    }

    fun dpToPx(dp: Float): Int {
        return (dp * getDisplayMetrics().density).toInt()
    }
}