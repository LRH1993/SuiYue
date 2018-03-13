package com.ruheng.suiyue.movie

import com.ruheng.suiyue.data.BookModel
import com.ruheng.suiyue.data.bean.MovieDetailBean
import com.ruheng.suiyue.network.Callback
import com.ruheng.suiyue.network.GsonParser
import com.ruheng.suiyue.network.OkhttpUtil
import java.io.IOException

/**
 * Created by lvruheng on 2018/3/1.
 */
class MovieDetailPresenter(view: MovieDetailContract.View) : MovieDetailContract.Presenter {
    override fun start(ibsn: String) {
        mView?.let {
            val okhttpUtil = OkhttpUtil.getInstance(mView?.getDetailContext()!!)
            model = BookModel(okhttpUtil)
            loadData(ibsn)
        }
    }

    override fun detachView() {
        mView = null
    }

    var mView: MovieDetailContract.View? = view
    private lateinit var model: BookModel

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }

    override fun loadData(ibsn: String) {
        var clazz = MovieDetailBean::class.java
        var parser = GsonParser(clazz)
        var callback: Callback<MovieDetailBean> = object : Callback<MovieDetailBean>(parser) {
            override fun onResponse(t: MovieDetailBean) {
                mView?.setMovie(t)
            }

            override fun onFailure(e: IOException) {
            }
        }

    }

}