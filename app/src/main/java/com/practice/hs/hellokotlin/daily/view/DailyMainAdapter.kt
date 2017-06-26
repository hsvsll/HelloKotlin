package com.practice.hs.hellokotlin.daily.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.SparseArray
import android.view.ViewGroup
import com.practice.hs.hellokotlin.bean.GankDailyContentResponse

/**
 * Created by huha on 2017/6/20.
 */

class DailyMainAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    val titleList = listOf("Android", "IOS", "瞎推荐", "福利", "休息视频")
    override fun getItem(position: Int): Fragment? {
        if (position == 4)
            return VideoFragment().newInstance(position)
        else
            return DailyDetailAndroidFragment().newInstance(position)
    }

    override fun getCount(): Int {
        return titleList.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titleList[position]
    }

}