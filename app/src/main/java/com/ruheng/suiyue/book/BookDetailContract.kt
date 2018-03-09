package com.ruheng.suiyue.book

import android.content.Context
import com.ruheng.suiyue.base.BasePresenter
import com.ruheng.suiyue.base.BaseView
import com.ruheng.suiyue.data.bean.BooksItem

/**
 * Created by lvruheng on 2018/3/1.
 */
interface BookDetailContract {
    interface View : BaseView<Presenter> {
        fun getBookContext(): Context?
        fun setBook(booksItem: BooksItem)
    }

    interface Presenter : BasePresenter {
        fun start(ibsn: String)
        fun loadData(ibsn: String)
    }
}