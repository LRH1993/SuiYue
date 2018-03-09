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
class MoviePresenter(view: MovieContract.View) : MovieContract.Presenter {

    override fun detachView() {
        mView = null
    }

    var mView: MovieContract.View? = view
    private lateinit var model: MovieModel

    init {
        view.setPresenter(this)
    }

    override fun start() {
        mView?.let {
            if (mView!!.isActive()) {
                val okhttpUtil = OkhttpUtil.getInstance(mView?.getMovieContext()!!)
                model = MovieModel(okhttpUtil)
                loadData()
                loadComingList()
                loadTopList()
            }
        }
    }

    override fun loadData() {
        var clazz = MovieListBean::class.java
        var parser = GsonParser(clazz)
        var callback: Callback<MovieListBean> = object : Callback<MovieListBean>(parser) {
            override fun onResponse(t: MovieListBean) {
                mView?.setOnlineList(t)
            }

            override fun onFailure(e: IOException) {
            }

        }
        model.getOnlineList(callback)

    }

    override fun loadComingList() {
        var clazz = MovieListBean::class.java
        var parser = GsonParser(clazz)
        var callback: Callback<MovieListBean> = object : Callback<MovieListBean>(parser) {
            override fun onResponse(t: MovieListBean) {
                mView?.setComingList(t)
            }

            override fun onFailure(e: IOException) {
            }

        }
        model.getComingList(callback)
    }

    override fun loadTopList() {
        var clazz = MovieListBean::class.java
        var parser = GsonParser(clazz)
        var callback: Callback<MovieListBean> = object : Callback<MovieListBean>(parser) {
            override fun onResponse(t: MovieListBean) {
                mView?.setTopList(t)
            }

            override fun onFailure(e: IOException) {
            }

        }
        model.getTopList(callback)
    }
}
