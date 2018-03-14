package com.ruheng.suiyue.article

import com.ruheng.suiyue.data.ArticleModel
import com.ruheng.suiyue.data.bean.ArticleDetailBean
import com.ruheng.suiyue.data.bean.QuestionContentBean
import com.ruheng.suiyue.data.bean.SerialContentBean
import com.ruheng.suiyue.network.Callback
import com.ruheng.suiyue.network.GsonParser
import com.ruheng.suiyue.network.OkhttpUtil
import java.io.IOException

/**
 * Created by lvruheng on 2018/3/1.
 */
class ArticleDetailPresenter(view: ArticleDetailContract.View) : ArticleDetailContract.Presenter {
    override fun start(category: String, item_id: String) {
        mView?.let {
            val okhttpUtil = OkhttpUtil.getInstance(mView?.getDetailContext()!!)
            model = ArticleModel(okhttpUtil)
            loadData(category, item_id)
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

    override fun loadData(category: String, item_id: String) {
        if (category == "2") {
            var clazz = SerialContentBean::class.java
            var parser = GsonParser(clazz)
            var callback: Callback<SerialContentBean> = object : Callback<SerialContentBean>(parser) {
                override fun onResponse(t: SerialContentBean) {
                    mView?.setSerial(t)
                }

                override fun onFailure(e: IOException) {
                }
            }
            model.getSerialDetail(category, item_id, callback)
        } else if (category == "3") {
            var clazz = QuestionContentBean::class.java
            var parser = GsonParser(clazz)
            var callback: Callback<QuestionContentBean> = object : Callback<QuestionContentBean>(parser) {
                override fun onResponse(t: QuestionContentBean) {
                    mView?.setQuestion(t)
                }

                override fun onFailure(e: IOException) {
                }
            }
            model.getQuestionDetail(category, item_id, callback)
        } else {
            var clazz = ArticleDetailBean::class.java
            var parser = GsonParser(clazz)
            var callback: Callback<ArticleDetailBean> = object : Callback<ArticleDetailBean>(parser) {
                override fun onResponse(t: ArticleDetailBean) {
                    mView?.setArticle(t)
                }

                override fun onFailure(e: IOException) {
                }
            }
            model.getDetail(category, item_id, callback)
        }

    }

}