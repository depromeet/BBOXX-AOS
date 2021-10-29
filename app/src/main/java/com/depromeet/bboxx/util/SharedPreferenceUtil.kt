package com.depromeet.bboxx.util

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceUtil {
    private var pref : SharedPreferences? = null

    fun initSharedPreference(context: Context, sharedKey: String){
        pref = context.getSharedPreferences(sharedKey, Context.MODE_PRIVATE)
    }

    fun setDataStringSharedPreference(data: String, dataKey: String){
        pref?.let{
            it.edit().putString(dataKey, data).commit()
        }
    }

    fun setDataIntSharedPreference(data: Int, dataKey: String){
        pref?.let{
            it.edit().putInt(dataKey, data).commit()
        }
    }

    fun setDataSharedPreference(data: Boolean, dataKey: String){
        pref?.let{
            it.edit().putBoolean(dataKey, data).commit()
        }
    }

    fun getDataStringSharedPreference(dataKey: String): String?
    {
        return pref?.getString(dataKey, "")
    }

    fun getDataIntSharedPreference(dataKey: String): Int?
    {
        return pref?.getInt(dataKey, 0)
    }

    fun getDataBooleanSharedPreference(dataKey: String): Boolean?
    {
        return pref?.getBoolean(dataKey, false)
    }

    fun delSharedPreference(sharedKey: String){
        pref?.edit()?.clear()?.apply()
    }
}