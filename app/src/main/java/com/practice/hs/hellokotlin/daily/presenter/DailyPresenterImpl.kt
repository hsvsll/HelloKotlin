package com.practice.hs.hellokotlin.daily.presenter

import com.practice.hs.hellokotlin.daily.model.DailyModelImpl
import com.practice.hs.hellokotlin.daily.view.DailyView
import rx.Subscription

/**
 * Created by huha on 2017/6/19.
 */
class DailyPresenterImpl(val dailyView: DailyView) : IDailyPresenter {
    val mDailyModelImpl: DailyModelImpl = DailyModelImpl()

    override fun requestDaily(year: String, month: String, day: String): Subscription{
        val subscription: Subscription = mDailyModelImpl.requestDailyContent(year, month, day).subscribe(
                {
                    result ->
                    when (result.error) {
                        false ->
                            dailyView?.dailyRequestSuccess(result.results)
                        else ->
                            dailyView?.dailyRequestFailed("失败原因：error为true")
                    }
                }, {
            error ->
            dailyView?.dailyRequestFailed("失败原因：" + error.message)
        })
        return subscription
    }

}