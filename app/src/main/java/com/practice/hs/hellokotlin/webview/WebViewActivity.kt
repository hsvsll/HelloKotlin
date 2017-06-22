package com.practice.hs.hellokotlin.webview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient
import com.practice.hs.hellokotlin.R
import kotlinx.android.synthetic.main.activity_web_view.*


class WebViewActivity : AppCompatActivity() {
    var url: String? = null
    fun toActivityNavigation(context: Context, url: String?){
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra("URL",url)
        context.startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        initView()
    }

    fun initView(){
        if(intent != null){
            url = intent.getStringExtra("URL")

            webView.loadUrl(url)

            //设置不用系统浏览器打开,直接显示在当前Webview
            webView.setWebViewClient(object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    view.loadUrl(url)
                    return true
                }
            })
        }

    }



}
