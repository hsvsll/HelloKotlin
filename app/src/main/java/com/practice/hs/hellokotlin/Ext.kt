package com.practice.hs.hellokotlin

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

/**
 * 辅助功能
 * Created by huha on 2017/6/19.
 */

fun loge(tag: String, content: String){
    Log.e(tag, content ?: tag)
}

fun formatDateTime(date: Date, strFormat: String): String{
    val formatter = SimpleDateFormat(strFormat,Locale.getDefault())
    return formatter.format(date)
}

fun formatDateTime(strDate: String, strFormat: String): String{
    var date: Date = parseDateTime(strDate,strFormat)
    var dateStr: String = formatDateTime(date,strFormat)
    return dateStr
}

fun parseDateTime(strDate: String, strFormat: String): Date{
    val formatter = SimpleDateFormat(strFormat,Locale.getDefault())
    return formatter.parse(strDate)
}

