package com.ruheng.suiyue.movie

import com.ruheng.suiyue.network.OkhttpUtil

/**
 * Created by lvruheng on 2018/3/1.
 */
class MoviePresenter(view: MovieContract.View) : MovieContract.Presenter {
    var mView = view
    init {
        view.setPresenter(this)
    }

    override fun start() {
        loadData()
    }

    override fun loadData() {
        if(mView.isActive()){
            val okhttpUtil = OkhttpUtil.getInstance(mView.getBookContext()!!)

        }

    }
}