package com.ruheng.suiyue.article

import com.ruheng.suiyue.data.ArticleModel
import com.ruheng.suiyue.data.bean.IdListBean
import com.ruheng.suiyue.data.bean.OneListBean
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
    var mView: ArticleContract.View? = view

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
        var parser = GsonParser(clazz)
        var callback: Callback<IdListBean> = object : Callback<IdListBean>(parser) {
            override fun onResponse(t: IdListBean) {
                if (t.data?.size!! > 0) {
                    val data = t.data[0]
                    loadList(data)
                }
            }

            override fun onFailure(e: IOException) {
            }

        }
        model.getIdList(callback)
    }

    override fun loadList(data: String) {
        var clazz = OneListBean::class.java
        var parser = GsonParser(clazz)
        var callback: Callback<OneListBean> = object : Callback<OneListBean>(parser) {
            override fun onResponse(t: OneListBean) {
                mView?.setOneList(t)

            }

            override fun onFailure(e: IOException) {

            }

        }
        model.getOneList(data, callback)
    }

}
