package com.ruheng.suiyue.book

import android.content.Context
import com.ruheng.suiyue.base.BasePresenter
import com.ruheng.suiyue.base.BaseView
import com.ruheng.suiyue.data.bean.BookListBean

/**
 * Created by lvruheng on 2018/3/1.
 */
interface SearchResultContract {
    interface View : BaseView<Presenter> {
        fun getSearchContext(): Context?
        fun setBookList(bookListBean: BookListBean)
    }

    interface Presenter : BasePresenter {
        fun start(keyword: String)
        fun loadData(keyword: String)
    }
}