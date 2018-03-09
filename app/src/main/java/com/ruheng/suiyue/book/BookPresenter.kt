package com.ruheng.suiyue.book

import com.ruheng.suiyue.data.BookModel
import com.ruheng.suiyue.data.bean.BookListBean
import com.ruheng.suiyue.network.Callback
import com.ruheng.suiyue.network.GsonParser
import com.ruheng.suiyue.network.OkhttpUtil
import java.io.IOException
import java.util.*

/**
 * Created by lvruheng on 2018/3/1.
 */
class BookPresenter(view: BookContract.View) : BookContract.Presenter {

    private var index: Int = Random().nextInt(200)+1
    override fun detachView() {
        mView = null
    }

    var mView: BookContract.View? = view
    private lateinit var model: BookModel

    init {
        view.setPresenter(this)
    }

    override fun start() {
        mView?.let {
            if (mView!!.isActive()) {
                val okhttpUtil = OkhttpUtil.getInstance(mView?.getBookContext()!!)
                model = BookModel(okhttpUtil)
                loadData()
            }
        }
    }

    override fun loadData() {
        var clazz = BookListBean::class.java
        var parser = GsonParser(clazz)
        var callback: Callback<BookListBean> = object : Callback<BookListBean>(parser) {
            override fun onResponse(t: BookListBean) {
                mView?.setBookList(t)

            }

            override fun onFailure(e: IOException) {
            }

        }
        model.getBookList(index, callback)
        index = index.inc()


    }
    override fun refreshData() {
        mView?.startFloatAnim()
        var clazz = BookListBean::class.java
        var parser = GsonParser(clazz)
        var callback: Callback<BookListBean> = object : Callback<BookListBean>(parser) {
            override fun onResponse(t: BookListBean) {
                mView?.setBookList(t)
                mView?.stopFloatAnim()

            }

            override fun onFailure(e: IOException) {
            }

        }
        model.getBookList(index, callback)
        index = index.inc()
    }

}