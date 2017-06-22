package com.practice.hs.hellokotlin.bean

/**
 * Created by huha on 2017/6/19.
 */

data class GankDailyContentItemResponse(val _id: String, val createdAt: String, val desc: String,
                                        val publishedAt: String, val type: String, val url: String, val used: Boolean, val who: String)