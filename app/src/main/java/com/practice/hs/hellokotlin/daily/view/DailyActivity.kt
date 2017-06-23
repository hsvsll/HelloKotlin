package com.practice.hs.hellokotlin.daily.view

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.widget.LinearLayout
import android.widget.Toast
import com.practice.hs.hellokotlin.AppClient
import com.practice.hs.hellokotlin.R
import com.practice.hs.hellokotlin.base.BaseActivity
import com.practice.hs.hellokotlin.bean.GankDailyContentResponse
import com.practice.hs.hellokotlin.daily.presenter.DailyPresenterImpl
import com.practice.hs.hellokotlin.daily.presenter.IDailyPresenter
import com.practice.hs.hellokotlin.event.RequestDailyDataSuccessEvent
import com.practice.hs.hellokotlin.loge
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import rx.Subscription
import java.util.*
import kotlin.properties.Delegates

class DailyActivity : BaseActivity(), DailyView {
    var mIDailyPresenter: IDailyPresenter by Delegates.notNull()
    var mAdapter: DailyMainAdapter by Delegates.notNull()
    var mLinearLayoutDivider: LinearLayout by Delegates.notNull()
    var data: GankDailyContentResponse? = null

    val subList: ArrayList<Subscription> by lazy {
        ArrayList<Subscription>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fab.setOnClickListener {
            val month: String = (Random().nextInt(8) + 6).toString()
            val day: String = (Random().nextInt(28) + 1).toString()
            loge("Time", "time = $month - $day")
            subList.add(mIDailyPresenter.requestDaily("2015", month, day))
        }
        mIDailyPresenter = DailyPresenterImpl(this)

        mLinearLayoutDivider = tabLayoutDaily.getChildAt(0) as LinearLayout
        mLinearLayoutDivider.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
        mLinearLayoutDivider.dividerDrawable = ContextCompat.getDrawable(this,
                R.drawable.tab_divider)

        mAdapter = DailyMainAdapter(supportFragmentManager)
        viewPageDaily.adapter = mAdapter
        viewPageDaily.offscreenPageLimit = 3
        tabLayoutDaily.setupWithViewPager(viewPageDaily)

        viewPageDaily.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                sendData()
            }
        })

        subList.add(mIDailyPresenter.requestDaily("2015", "08", "07"))

    }

    override fun dailyRequestFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun dailyRequestSuccess(result: GankDailyContentResponse) {
        data = result
        sendData()
    }

    fun sendData() {
        if (data != null) {
            AppClient.send(RequestDailyDataSuccessEvent(data, viewPageDaily.currentItem))
        }
    }

    override fun onDestroy() {
        subList.forEach {
            if (!it.isUnsubscribed) {
                it.unsubscribe()
            }
        }
        super.onDestroy()
    }

}

