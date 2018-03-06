package com.ruheng.suiyue.article

import android.content.Context
import android.os.Bundle
import com.ruheng.suiyue.R
import com.ruheng.suiyue.base.BaseFragment

/**
 * Created by lvruheng on 2018/2/28.
 */
class ArticleFragment : BaseFragment(), ArticleContract.View {
    lateinit var mPresenter: ArticlePresenter
    var mLastRefreshTime: Long = 0
    override fun getLayoutResources(): Int {
        return R.layout.fragment_article
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            //距离上次刷新超过6分钟，重新加载数据
            if (System.currentTimeMillis().minus(mLastRefreshTime) > 3600000) {
                mPresenter.start()
            }
            mLastRefreshTime = System.currentTimeMillis()
        }
    }

    override fun setPresenter(presenter: ArticleContract.Presenter) {
        mPresenter = presenter as ArticlePresenter
    }

    override fun setLoadingIndicator() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoadingError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isActive(): Boolean {
        return isAdded
    }

    override fun getBookContext(): Context? {
        return if (isActive()) {
            context
        } else {
            null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

}