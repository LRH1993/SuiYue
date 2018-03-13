package com.ruheng.suiyue.movie

import com.ruheng.suiyue.data.MovieModel
import com.ruheng.suiyue.data.bean.MovieListBean
import com.ruheng.suiyue.network.Callback
import com.ruheng.suiyue.network.GsonParser
import com.ruheng.suiyue.network.OkhttpUtil
import java.io.IOException

/**
 * Created by lvruheng on 2018/3/1.
 */
class MovieListPresenter(view: MovieListContract.View) : MovieListContract.Presenter {

    override fun detachView() {
        mView = null
    }

    var mView: MovieListContract.View? = view
    private lateinit var model: MovieModel

    init {
        view.setPresenter(this)
    }

    override fun start() {
        val okhttpUtil = OkhttpUtil.getInstance(mView?.getMovieListContext()!!)
        model = MovieModel(okhttpUtil)

    }

    override fun loadData(start: Int,type:Int) {
        var clazz = MovieListBean::class.java
        var parser = GsonParser(clazz)
        var callback: Callback<MovieListBean> = object : Callback<MovieListBean>(parser) {
            override fun onResponse(t: MovieListBean) {
                mView?.setList(t)
            }

            override fun onFailure(e: IOException) {
            }

        }
        model.getMoreList(start,callback,type)

    }
}