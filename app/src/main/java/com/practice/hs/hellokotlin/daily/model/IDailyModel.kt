package com.practice.hs.hellokotlin.daily.model

import com.practice.hs.hellokotlin.bean.GankDailyResponse
import rx.Observable

/**
 * Created by huha on 2017/6/19.
 */
interface IDailyModel{
    fun requestDailyContent(day: String, month: String, year: String): Observable<GankDailyResponse>
}