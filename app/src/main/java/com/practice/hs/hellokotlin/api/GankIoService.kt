package com.practice.hs.hellokotlin.api

import com.practice.hs.hellokotlin.bean.GankDailyResponse
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Created by huha on 2017/6/19.
 */
interface GankIoService {

    @GET("/api/day/{year}/{month}/{day}")
    fun dailyContent(
            @Path("year") day: String,
            @Path("month") month: String,
            @Path("day") year: String
    ): Observable<GankDailyResponse>
}