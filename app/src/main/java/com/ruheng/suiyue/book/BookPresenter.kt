package com.ruheng.suiyue.book

/**
 * Created by lvruheng on 2018/3/1.
 */
class BookPresenter(view: BookContract.View) : BookContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {
        loadData()
    }

    override fun loadData() {

    }
}