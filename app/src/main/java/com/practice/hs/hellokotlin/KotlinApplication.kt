package com.practice.hs.hellokotlin

import android.app.Application

/**
 * Created by huha on 2017/6/19.
 */
class KotlinApplication : Application() {
    companion object{
        private var instance: Application? = null
        fun instance() = instance!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


}