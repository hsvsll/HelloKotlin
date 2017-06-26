package com.practice.hs.hellokotlin.daily.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.MediaController
import com.practice.hs.hellokotlin.AppClient
import com.practice.hs.hellokotlin.R
import com.practice.hs.hellokotlin.event.RequestDailyDataSuccessEvent
import kotlinx.android.synthetic.main.fragment_video.*
import rx.Subscription

/**
 * Created by qiyue on 17/6/26.
 */
 class VideoFragment : Fragment() {
    var pageType: Int? = null
    var rxSubscription: Subscription? = null
    fun newInstance(pageType: Int): VideoFragment? {
        var fragment: VideoFragment? = VideoFragment()
        var arg: Bundle? = Bundle()
        arg?.putInt("TYPE",pageType)
        fragment?.arguments = arg
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_video, container, false)
        iniView()
        return view
    }

    private fun iniView() {
        rxSubscription = AppClient.toObserverable(RequestDailyDataSuccessEvent::class.java)
                .subscribe({
                    dataEvent ->
                    if(pageType == dataEvent.type){
//                        val uri = Uri.parse(dataEvent.data?.休息视频?.get(0)?.url)
//                        val intent = Intent(Intent.ACTION_VIEW)
//                        intent.setDataAndType(uri, "video/mp4")
//                        startActivity(intent)

//                        var uri = Uri.parse(dataEvent.data?.休息视频?.get(0)?.url)
//                        vvPlay.setMediaController(MediaController(activity))
//                        vvPlay.setVideoURI(uri)
//                        vvPlay.start()
//                        vvPlay.requestFocus()

                        wvVideo.loadUrl(dataEvent.data?.休息视频?.get(0)?.url)

                        //设置不用系统浏览器打开,直接显示在当前Webview
                        wvVideo.setWebViewClient(object : WebViewClient() {
                            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                                view.loadUrl(url)
                                return true
                            }
                        })
                    }
                })

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            pageType = arguments.get("TYPE") as Int?
        }
    }

    override fun onDestroy() {
        if(rxSubscription != null && !rxSubscription?.isUnsubscribed!!){
            rxSubscription?.unsubscribe()
        }
        super.onDestroy()
    }
}