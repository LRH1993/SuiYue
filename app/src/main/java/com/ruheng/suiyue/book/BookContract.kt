package com.ruheng.suiyue.book

import android.content.Context
import com.ruheng.suiyue.base.BasePresenter
import com.ruheng.suiyue.base.BaseView

/**
 * Created by lvruheng on 2018/3/1.
 */
interface BookContract {
    interface View : BaseView<Presenter> {
        fun setLoadingIndicator()
        fun showLoadingError()
        fun isActive(): Boolean
        fun getBookContext():Context?
    }

    interface Presenter : BasePresenter {
        fun loadData()
    }
}