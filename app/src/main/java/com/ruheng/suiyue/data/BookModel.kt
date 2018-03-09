package com.ruheng.suiyue.data

import com.ruheng.suiyue.data.bean.BookListBean
import com.ruheng.suiyue.data.bean.BooksItem
import com.ruheng.suiyue.network.Api
import com.ruheng.suiyue.network.Callback
import com.ruheng.suiyue.network.OkhttpUtil

/**
 * Created by lvruheng on 2018/3/1.
 */
class BookModel(okhttpUtil: OkhttpUtil) {
    var mOkhttpUtil: OkhttpUtil = okhttpUtil
    var BOOK_LIST_URL = "series/id/books"
    var BOOK_DETAIL_URL = "isbn/name"
    fun getBookList(id: Int, callback: Callback<BookListBean>) {
        val url = Api.BASE_BOOK_URL + BOOK_LIST_URL.replace("id", id.toString(), false)
        mOkhttpUtil.getDataAsync(url, callback)
    }

    fun getBookDetail(ibsn: String, callback: Callback<BooksItem>) {
        val url = Api.BASE_BOOK_URL + BOOK_DETAIL_URL.replace("name", ibsn, false)
        mOkhttpUtil.getDataAsync(url, callback)
    }
}