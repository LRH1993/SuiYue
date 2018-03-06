package com.ruheng.suiyue.book

import android.content.Context
import android.os.Bundle
import com.ruheng.suiyue.R
import com.ruheng.suiyue.base.BaseFragment

/**
 * Created by lvruheng on 2018/2/28.
 */
class BookFragment : BaseFragment(), BookContract.View {

    lateinit var mPresenter: BookPresenter
    var mLastRefreshTime: Long = 0
    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun onResume() {
        super.onResume()
        //距离上次刷新超过6分钟，重新加载数据
        if (System.currentTimeMillis().minus(mLastRefreshTime) > 3600000) {
            mPresenter.start()
        }
        mLastRefreshTime = System.currentTimeMillis()
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_book
    }

    override fun setPresenter(presenter: BookContract.Presenter) {
        mPresenter = presenter as BookPresenter
    }

    override fun getBookContext(): Context? {
        return if (isActive()) {
            context
        } else {
            null
        }
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

    override fun setLoadingIndicator() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoadingError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isActive(): Boolean {
        return isAdded
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

}