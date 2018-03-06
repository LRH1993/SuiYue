package com.ruheng.suiyue.article

import android.util.Log
import com.ruheng.suiyue.data.ArticleModel
import com.ruheng.suiyue.data.bean.IdListBean
import com.ruheng.suiyue.network.Callback
import com.ruheng.suiyue.network.GsonParser
import com.ruheng.suiyue.network.OkhttpUtil
import java.io.IOException

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
                loadData()
            }
        }
    }

    override fun loadData() {
        var clazz = IdListBean::class.java
        var parser = GsonParser<IdListBean>(clazz)
        var callback: Callback<IdListBean> = object : Callback<IdListBean>(parser) {
            override fun onResponse(t: IdListBean) {
                Log.e("日志","条数"+t.data?.size)
            }

            override fun onFailure(e: IOException) {
            }

        }
        model.getIdList(callback)
    }
}