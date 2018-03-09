package com.ruheng.suiyue.article

import android.content.Context
import com.ruheng.suiyue.base.BasePresenter
import com.ruheng.suiyue.base.BaseView
import com.ruheng.suiyue.data.bean.OneListBean

/**
 * Created by lvruheng on 2018/3/1.
 */
interface ArticleContract {
    interface View : BaseView<Presenter> {
        fun isActive(): Boolean
        fun getArticleContext(): Context?
        fun setOneList(oneListBean: OneListBean)
    }

    interface Presenter : BasePresenter {
        fun loadData()
        fun loadList(data: String)
        fun loadMore(index:Int)
    }
}