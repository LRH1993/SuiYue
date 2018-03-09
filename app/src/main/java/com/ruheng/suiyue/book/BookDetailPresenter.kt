package com.ruheng.suiyue.book

import com.ruheng.suiyue.data.BookModel
import com.ruheng.suiyue.data.bean.BooksItem
import com.ruheng.suiyue.network.Callback
import com.ruheng.suiyue.network.GsonParser
import com.ruheng.suiyue.network.OkhttpUtil
import java.io.IOException

/**
 * Created by lvruheng on 2018/3/1.
 */
class BookDetailPresenter(view: BookDetailContract.View) : BookDetailContract.Presenter {
    override fun start(ibsn: String) {
        mView?.let {
            val okhttpUtil = OkhttpUtil.getInstance(mView?.getBookContext()!!)
            model = BookModel(okhttpUtil)
            loadData(ibsn)
        }
    }

    override fun detachView() {
        mView = null
    }

    var mView: BookDetailContract.View? = view
    private lateinit var model: BookModel

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }

    override fun loadData(ibsn: String) {
        var clazz = BooksItem::class.java
        var parser = GsonParser(clazz)
        var callback: Callback<BooksItem> = object : Callback<BooksItem>(parser) {
            override fun onResponse(t: BooksItem) {
                mView?.setBook(t)
            }

            override fun onFailure(e: IOException) {
            }
        }
        model.getBookDetail(ibsn, callback)
    }

}