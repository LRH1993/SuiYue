package com.ruheng.suiyue.article

import android.content.Context
import com.ruheng.suiyue.base.BasePresenter
import com.ruheng.suiyue.base.BaseView

/**
 * Created by lvruheng on 2018/3/1.
 */
interface ArticleContract {
    interface View : BaseView<Presenter> {
        fun setLoadingIndicator()
        fun showLoadingError()
        fun isActive(): Boolean
        fun getBookContext(): Context?
    }

    interface Presenter : BasePresenter {
        fun loadData()
    }
}