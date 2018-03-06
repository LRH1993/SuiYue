package com.ruheng.suiyue.movie


import com.ruheng.suiyue.network.OkhttpUtil

/**
 * Created by lvruheng on 2018/3/1.
 */
class MoviePresenter(view: MovieContract.View) : MovieContract.Presenter {
    override fun detachView() {
        mView = null
    }

    var mView: MovieContract.View? = view

    init {
        view.setPresenter(this)
    }

    override fun start() {
        loadData()
    }

    override fun loadData() {
        mView?.let {
            if (mView!!.isActive()) {
                val okhttpUtil = OkhttpUtil.getInstance(mView?.getBookContext()!!)

            }
        }


    }
}