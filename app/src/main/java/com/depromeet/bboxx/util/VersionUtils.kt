package com.depromeet.bboxx.util

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi

object VersionUtils {

    fun equalsAPI19(): Boolean {
        return Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT
    }

    fun isOverAPI19(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
    }

    fun isOverAPI21(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
    }

    fun isOverAPI22(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1
    }

    fun isOverAPI23(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }

    fun equalsAPI23(): Boolean {
        return Build.VERSION.SDK_INT == Build.VERSION_CODES.M
    }

    fun isOverAPI24(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
    }

    fun equalsAPI24(): Boolean {
        return Build.VERSION.SDK_INT == Build.VERSION_CODES.N
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun appVersionInfo(context: Context) : String{
        val appPackage = context.packageName

        val packageManager = context.packageManager
        val flag = 0;
        return try {
            val info: PackageInfo = packageManager.getPackageInfo(
                appPackage, flag
            )
            info.versionName

        } catch (e: PackageManager.NameNotFoundException) {
            ""
        }
    }
}