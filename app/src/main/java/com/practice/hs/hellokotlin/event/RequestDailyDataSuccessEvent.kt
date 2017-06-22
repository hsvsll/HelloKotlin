package com.practice.hs.hellokotlin.event

import com.practice.hs.hellokotlin.bean.GankDailyContentResponse
import java.io.Serializable

/**
 * Created by huha on 2017/6/21.
 */

data class RequestDailyDataSuccessEvent(var data: GankDailyContentResponse?,var type: Int) : Serializable