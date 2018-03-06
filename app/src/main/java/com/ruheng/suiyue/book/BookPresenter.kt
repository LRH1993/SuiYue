package com.ruheng.suiyue.book

import com.ruheng.suiyue.data.BookModel
import com.ruheng.suiyue.network.OkhttpUtil

/**
 * Created by lvruheng on 2018/3/1.
 */
class BookPresenter(view: BookContract.View) : BookContract.Presenter {
    override fun detachView() {
        mView = null
    }

    var mView: BookContract.View? = view
    private lateinit var model: BookModel

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
                model = BookModel(okhttpUtil)

            }
        }

    }
}