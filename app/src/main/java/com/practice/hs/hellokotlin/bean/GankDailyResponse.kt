package com.practice.hs.hellokotlin.bean

import java.util.*

/**
 * 每日数据Response结构
 * Created by huha on 2017/6/19.
 */



data class GankDailyResponse(val category: ArrayList<String>, val error: Boolean, val results: GankDailyContentResponse)