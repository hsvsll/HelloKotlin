package com.practice.hs.hellokotlin.bean

import java.io.Serializable
import java.util.*

/**
 * Created by huha on 2017/6/19.
 */


data class GankDailyContentResponse(val Android: ArrayList<GankDailyContentItemResponse>, val iOS: ArrayList<GankDailyContentItemResponse>
                                    , val 休息视频: ArrayList<GankDailyContentItemResponse>, val 拓展资源: ArrayList<GankDailyContentItemResponse>, val 瞎推荐: ArrayList<GankDailyContentItemResponse>
                                    , val 福利: ArrayList<GankDailyContentItemResponse>) : Serializable