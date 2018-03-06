package com.ruheng.suiyue.movie

import android.content.Context
import android.os.Bundle
import com.ruheng.suiyue.R
import com.ruheng.suiyue.base.BaseFragment

/**
 * Created by lvruheng on 2018/2/28.
 */
class MovieFragment : BaseFragment(), MovieContract.View {
    lateinit var mPresenter: MoviePresenter
    var mLastRefreshTime: Long = 0
    override fun getLayoutResources(): Int {
        return R.layout.fragment_movie
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

    override fun setPresenter(presenter: MovieContract.Presenter) {
        mPresenter = presenter as MoviePresenter
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