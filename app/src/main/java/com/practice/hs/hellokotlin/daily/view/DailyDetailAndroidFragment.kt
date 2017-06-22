package com.practice.hs.hellokotlin.daily.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.practice.hs.hellokotlin.AppClient
import com.practice.hs.hellokotlin.R
import com.practice.hs.hellokotlin.bean.GankDailyContentResponse
import com.practice.hs.hellokotlin.event.RequestDailyDataSuccessEvent
import com.practice.hs.hellokotlin.loge
import rx.Subscription


/**
 *
 * Created by huha on 2017/6/20.
 */

class DailyDetailAndroidFragment : Fragment() {
    var pageType: Int? = null
    var data: GankDailyContentResponse? = null
    var mDailyItemAdapter: DailyDetailAdapter? = null
    var rxSubscription: Subscription? = null


    fun newInstance(pageType: Int): DailyDetailAndroidFragment? {
        var fragment: DailyDetailAndroidFragment? = DailyDetailAndroidFragment()
        var arg: Bundle? = Bundle()
        arg?.putInt("TYPE",pageType)
        fragment?.arguments = arg
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mainView = inflater?.inflate(R.layout.daily_detail_content_fragment, container, false)
        initView(mainView)
        return mainView
    }

    fun initView(mainView: View?) {
        val forecastList = mainView?.findViewById(R.id.rvDailyItem) as RecyclerView
        mDailyItemAdapter = DailyDetailAdapter(activity)
        forecastList?.adapter = mDailyItemAdapter
        forecastList?.layoutManager = LinearLayoutManager(activity)
        rxSubscription = AppClient.toObserverable(RequestDailyDataSuccessEvent::class.java)
                .subscribe({
                    dataEvent ->
                    if(pageType == dataEvent.type){
                        loge("pageType", "type = "+dataEvent.type)
                        mDailyItemAdapter?.setTestData(dataEvent.data,dataEvent.type)
                    }
                })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            pageType = arguments.get("TYPE") as Int?
//            data = arguments.getSerializable("DATA") as GankDailyContentResponse
        }
    }

    override fun onDestroy() {
        if(rxSubscription != null && !rxSubscription?.isUnsubscribed!!){
            rxSubscription?.unsubscribe()
        }
        super.onDestroy()
    }



}