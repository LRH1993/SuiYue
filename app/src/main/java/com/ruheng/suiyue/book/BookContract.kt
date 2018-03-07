package com.ruheng.suiyue.book

import android.content.Context
import com.ruheng.suiyue.base.BasePresenter
import com.ruheng.suiyue.base.BaseView
import com.ruheng.suiyue.data.bean.BookListBean

/**
 * Created by lvruheng on 2018/3/1.
 */
interface BookContract {
    interface View : BaseView<Presenter> {
        fun startFloatAnim()
        fun stopFloatAnim()
        fun isActive(): Boolean
        fun getBookContext():Context?
        fun setBookList(bookListBean: BookListBean)
    }

    interface Presenter : BasePresenter {
        fun loadData()
        fun refreshData()
    }
}