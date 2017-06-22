package com.practice.hs.hellokotlin

import com.practice.hs.hellokotlin.api.GankIoService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.subjects.PublishSubject
import rx.subjects.SerializedSubject
import java.util.concurrent.TimeUnit

/**
 * Created by huha on 2017/6/19.
 */

class  AppClient<T> {
    companion object{
        fun getRetrofit(): Retrofit{
            //日志显示级别
            val level: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY
            //新建log拦截器
            val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                message -> loge("AppClient", "OkHttp："+message)
            })
            loggingInterceptor.level = level

            val okHttpClient = OkHttpClient().newBuilder()
            okHttpClient.connectTimeout(60, TimeUnit.SECONDS)
            okHttpClient.readTimeout(10, TimeUnit.SECONDS)

            //OkHttp进行添加拦截器loggingInterceptor
            okHttpClient.addInterceptor(loggingInterceptor)

            return Retrofit.Builder()
                    .baseUrl(AppConfig.REQUEST_BASE_URL)
                    .client(okHttpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()
        }

        val appService: GankIoService = getAppService(GankIoService::class.java)

        val mRxBus: SerializedSubject<Any,Any> = SerializedSubject<Any,Any>(PublishSubject.create())

        fun <T> getAppService(service: Class<T>): T{
            return getRetrofit().create(service)
        }

        fun send(any: Any){
            mRxBus.onNext(any)
        }

        fun <T>toObserverable(eventType: Class<T>): Observable<T>{
            return mRxBus.ofType(eventType)
        }

    }

}