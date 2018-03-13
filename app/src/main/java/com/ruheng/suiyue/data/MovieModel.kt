package com.ruheng.suiyue.data

import com.ruheng.suiyue.data.bean.MovieDetailBean
import com.ruheng.suiyue.data.bean.MovieListBean
import com.ruheng.suiyue.network.Api
import com.ruheng.suiyue.network.Callback
import com.ruheng.suiyue.network.OkhttpUtil

/**
 * Created by lvruheng on 2018/3/5.
 */
class MovieModel(okhttpUtil: OkhttpUtil) {
    var mOkhttpUtil: OkhttpUtil = okhttpUtil
    //上映的电影
    var ONLINE_URL = "in_theaters"
    //即将上映的电影
    var COMING_URL = "coming_soon"
    //top 250 电影
    var TOP_URL = "top250"
    //电影详情信息
    var DETAIL_URL = "subject/id"

    fun getOnlineList(callback: Callback<MovieListBean>) {
        var url = Api.BASE_MOVIE_URL + ONLINE_URL
        mOkhttpUtil.getDataAsync(url, callback)
    }

    fun getComingList(callback: Callback<MovieListBean>) {
        var url = Api.BASE_MOVIE_URL + COMING_URL
        mOkhttpUtil.getDataAsync(url, callback)
    }

    fun getTopList(callback: Callback<MovieListBean>) {
        var url = Api.BASE_MOVIE_URL + TOP_URL
        mOkhttpUtil.getDataAsync(url, callback)
    }

    fun getMoreList(start: Int, callback: Callback<MovieListBean>, type: Int) {
        var url: String = when (type) {
            1 -> Api.BASE_MOVIE_URL + ONLINE_URL + "?start=" + start
            2 -> Api.BASE_MOVIE_URL + COMING_URL + "?start=" + start
            else -> Api.BASE_MOVIE_URL + TOP_URL + "?start=" + start
        }

        mOkhttpUtil.getDataAsync(url, callback)
    }

    fun getMovieDetail(id: String, callback: Callback<MovieDetailBean>) {
        var url = Api.BASE_MOVIE_URL + DETAIL_URL.replace("id", id.toString())
        mOkhttpUtil.getDataAsync(url, callback)
    }

}