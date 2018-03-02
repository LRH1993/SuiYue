package com.ruheng.suiyue.article

import com.ruheng.suiyue.network.OkhttpUtil

/**
 * Created by lvruheng on 2018/3/1.
 */
class ArticlePresenter(view: ArticleContract.View) : ArticleContract.Presenter {
    var mView = view
    init {
        view.setPresenter(this)
    }

    override fun start() {
        loadData()
    }

    override fun loadData() {
        if(mView.isActive()){
            val okhttpClient = OkhttpUtil.getInstance(mView.getBookContext()!!)

        }

    }
}