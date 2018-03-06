package com.ruheng.suiyue.article

import com.ruheng.suiyue.data.ArticleModel
import com.ruheng.suiyue.network.OkhttpUtil

/**
 * Created by lvruheng on 2018/3/1.
 */
class ArticlePresenter(view: ArticleContract.View) : ArticleContract.Presenter {
    override fun detachView() {
        mView = null
    }

    private lateinit var model: ArticleModel
    var mView:ArticleContract.View? = view

    init {
        view.setPresenter(this)
    }

    override fun start() {
        mView?.let {
            if (mView!!.isActive()) {
                val okhttpUtil = OkhttpUtil.getInstance(mView?.getBookContext()!!)
                model = ArticleModel(okhttpUtil)

            }
        }
        loadData()
    }

    override fun loadData() {

    }
}