package com.ruheng.suiyue.book

import com.ruheng.suiyue.data.BookModel
import com.ruheng.suiyue.data.bean.BookListBean
import com.ruheng.suiyue.network.Callback
import com.ruheng.suiyue.network.GsonParser
import com.ruheng.suiyue.network.OkhttpUtil
import java.io.IOException

/**
 * Created by lvruheng on 2018/3/1.
 */
class SearchResultPresenter(view: SearchResultContract.View) : SearchResultContract.Presenter {
    override fun start(keyword: String) {
        mView?.let {
            val okhttpUtil = OkhttpUtil.getInstance(mView?.getSearchContext()!!)
            model = BookModel(okhttpUtil)
            loadData(keyword)
        }
    }

    override fun detachView() {
        mView = null
    }

    var mView: SearchResultContract.View? = view
    private lateinit var model: BookModel

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }

    override fun loadData(keyword: String) {
        var clazz = BookListBean::class.java
        var parser = GsonParser(clazz)
        var callback: Callback<BookListBean> = object : Callback<BookListBean>(parser) {
            override fun onResponse(t: BookListBean) {
                mView?.setBookList(t)
            }

            override fun onFailure(e: IOException) {
            }
        }
        model.getSearchList(keyword, callback)
    }

}