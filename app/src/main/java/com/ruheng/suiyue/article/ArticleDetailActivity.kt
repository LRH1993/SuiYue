package com.ruheng.suiyue.article

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gyf.barlibrary.ImmersionBar
import com.ruheng.suiyue.R
import com.ruheng.suiyue.data.bean.ArticleDetailBean
import kotlinx.android.synthetic.main.activity_article_detail.*

/**
 * Created by lvruheng on 2018/3/7.
 */
class ArticleDetailActivity : AppCompatActivity(), ArticleDetailContract.View {
    private lateinit var mPresenter: ArticleDetailPresenter
    private var item_id: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)
        ImmersionBar.with(this).transparentBar().barAlpha(0.3f).fitsSystemWindows(true).init()
        val extras = intent.extras
        item_id = extras.get("item_id") as String

    }

    override fun onResume() {
        super.onResume()
        mPresenter = ArticleDetailPresenter(this)
        mPresenter.start(item_id)
    }

    override fun setPresenter(presenter: ArticleDetailContract.Presenter) {
        //省略
    }

    override fun getDetailContext(): Context? {
        return this
    }

    override fun setArticle(articleDetailBean: ArticleDetailBean) {
        val data = articleDetailBean.data
        val hpTitle = data.hpTitle
        tv_article_title.text = hpTitle
    }
}