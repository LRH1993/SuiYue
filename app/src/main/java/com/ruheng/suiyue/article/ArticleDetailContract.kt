package com.ruheng.suiyue.article

import android.content.Context
import com.ruheng.suiyue.base.BasePresenter
import com.ruheng.suiyue.base.BaseView
import com.ruheng.suiyue.data.bean.ArticleDetailBean

/**
 * Created by lvruheng on 2018/3/1.
 */
interface ArticleDetailContract {
    interface View : BaseView<Presenter> {
        fun getDetailContext(): Context?
        fun setArticle(articleDetailBean: ArticleDetailBean)
    }

    interface Presenter : BasePresenter {
        fun start(item_id: String)
        fun loadData(item_id: String)
    }
}