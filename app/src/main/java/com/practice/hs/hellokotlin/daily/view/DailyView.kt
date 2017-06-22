package com.practice.hs.hellokotlin.daily.view

import com.practice.hs.hellokotlin.bean.GankDailyContentResponse

/**
 * 每日数据回调接口
 * Created by huha on 2017/6/19.
 */
interface DailyView {
    fun dailyRequestSuccess(result: GankDailyContentResponse)

    fun dailyRequestFailed(message: String)
}