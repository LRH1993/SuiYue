package com.ruheng.suiyue.article

import com.ruheng.suiyue.data.ArticleModel
import com.ruheng.suiyue.data.bean.ArticleDetailBean
import com.ruheng.suiyue.network.Callback
import com.ruheng.suiyue.network.GsonParser
import com.ruheng.suiyue.network.OkhttpUtil
import java.io.IOException

/**
 * Created by lvruheng on 2018/3/1.
 */
class ArticleDetailPresenter(view: ArticleDetailContract.View) : ArticleDetailContract.Presenter {
    override fun start(item_id: String) {
        mView?.let {
            val okhttpUtil = OkhttpUtil.getInstance(mView?.getDetailContext()!!)
            model = ArticleModel(okhttpUtil)
            loadData(item_id)
        }
    }

    override fun detachView() {
        mView = null
    }

    var mView: ArticleDetailContract.View? = view
    private lateinit var model: ArticleModel

    init {
        view.setPresenter(this)
    }

    override fun start() {

    }

    override fun loadData(item_id: String) {
        var clazz = ArticleDetailBean::class.java
        var parser = GsonParser(clazz)
        var callback: Callback<ArticleDetailBean> = object : Callback<ArticleDetailBean>(parser) {
            override fun onResponse(t: ArticleDetailBean) {
                mView?.setArticle(t)
            }

            override fun onFailure(e: IOException) {
            }
        }
        model.getDetail(item_id, callback)
    }

}