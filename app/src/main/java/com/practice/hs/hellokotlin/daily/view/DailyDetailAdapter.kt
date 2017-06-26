package com.practice.hs.hellokotlin.daily.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.practice.hs.hellokotlin.R
import com.practice.hs.hellokotlin.bean.GankDailyContentItemResponse
import com.practice.hs.hellokotlin.bean.GankDailyContentResponse
import com.practice.hs.hellokotlin.formatDateTime
import com.practice.hs.hellokotlin.webview.WebViewActivity
import kotlinx.android.synthetic.main.daily_adapter_item.view.*
import java.util.*

/**
 * Created by huha on 2017/6/20.
 */
class DailyDetailAdapter(context: Context) :
        RecyclerView.Adapter<DailyDetailAdapter.ViewHolder>() {
    var data: ArrayList<GankDailyContentItemResponse>? = ArrayList()

    companion object {
        var pageType: Int? = null
        var mContext: Context? = null
    }

    init {
        mContext = context
    }

    fun setTestData(items: GankDailyContentResponse?, type: Int) {
        pageType = type
        when (type) {
            0 ->
                data = items?.Android
            1 ->
                data = items?.iOS
            2 ->
                data = items?.瞎推荐
            3 ->
                data = items?.福利
        }
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(parent?.context).inflate(R.layout.daily_adapter_item, parent, false)
        return DailyDetailAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        val count = data?.size ?: 0
        return count
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindForecast(data?.get(position))
    }

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun bindForecast(item: GankDailyContentItemResponse?) {
            with(item!!) {
                if (pageType == 3) {
                    view?.rlDailyItemContent?.visibility = View.GONE
                    view?.tvWelfare?.visibility = View.VISIBLE
                    Glide.with(mContext).load(url).into(view?.tvWelfare)
                } else if(pageType == 4){

                }else{
                    view?.rlDailyItemContent?.visibility = View.VISIBLE
                    view?.tvWelfare?.visibility = View.GONE
                    view?.tvArticleType?.text = type
                    view?.tvPublishedTime?.text = formatDateTime(publishedAt, "yyyy-MM-dd")
                    view?.tvArticleDesc?.text = desc
                    view?.tvArticleAddress?.text = "作者：$who"
                    view?.rlDailyItemContent?.setOnClickListener {
                        WebViewActivity().toActivityNavigation(mContext!!, url)
                    }
                }
            }
        }
    }

}