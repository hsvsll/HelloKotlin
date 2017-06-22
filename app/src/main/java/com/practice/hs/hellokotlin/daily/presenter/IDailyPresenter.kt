package com.practice.hs.hellokotlin.daily.presenter

import rx.Subscription

/**
 * Created by huha on 2017/6/19.
 */
interface IDailyPresenter {
    fun requestDaily(year: String, month:
    String, day: String): Subscription
}