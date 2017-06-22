package com.practice.hs.hellokotlin.daily.model

import com.practice.hs.hellokotlin.AppClient
import com.practice.hs.hellokotlin.bean.GankDailyResponse
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 每日数据model实现
 * Created by huha on 2017/6/19.
 */

class DailyModelImpl : IDailyModel{

    override fun requestDailyContent(year: String, month: String, day: String): Observable<GankDailyResponse> {
        return AppClient.appService
                .dailyContent(year,month,day)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}