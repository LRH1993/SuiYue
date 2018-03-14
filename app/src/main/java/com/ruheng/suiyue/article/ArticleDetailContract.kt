package com.ruheng.suiyue.article

import android.content.Context
import com.ruheng.suiyue.base.BasePresenter
import com.ruheng.suiyue.base.BaseView
import com.ruheng.suiyue.data.bean.ArticleDetailBean
import com.ruheng.suiyue.data.bean.QuestionContentBean
import com.ruheng.suiyue.data.bean.SerialContentBean

/**
 * Created by lvruheng on 2018/3/1.
 */
interface ArticleDetailContract {
    interface View : BaseView<Presenter> {
        fun getDetailContext(): Context?
        fun setArticle(articleDetailBean: ArticleDetailBean)
        fun setSerial(serialContentBean: SerialContentBean)
        fun setQuestion(questionContentBean: QuestionContentBean)
    }

    interface Presenter : BasePresenter {
        fun start(category: String,item_id: String)
        fun loadData(category: String,item_id: String)
    }
}