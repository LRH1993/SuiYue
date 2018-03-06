package com.ruheng.suiyue.article

import android.content.Context
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import com.ruheng.suiyue.R
import com.ruheng.suiyue.base.BaseFragment
import com.ruheng.suiyue.data.bean.ContentListItem
import com.ruheng.suiyue.data.bean.OneListBean
import kotlinx.android.synthetic.main.fragment_article.*

/**
 * Created by lvruheng on 2018/2/28.
 */
class ArticleFragment : BaseFragment(), ArticleContract.View, SwipeRefreshLayout.OnRefreshListener {

    lateinit var mPresenter: ArticlePresenter
    var mLastRefreshTime: Long = 0
    var mIsRefresh: Boolean = false
    private var mAdapter: ArticleAdapter? = null
    var mList = ArrayList<ContentListItem>()
    override fun getLayoutResources(): Int {
        return R.layout.fragment_article
    }

    override fun initView(savedInstanceState: Bundle?) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = ArticleAdapter(context!!, mList)
        recyclerView.adapter = mAdapter
        refreshLayout.setOnRefreshListener(this)
    }


    override fun setOneList(oneListBean: OneListBean) {
        if (mIsRefresh) {
            mIsRefresh = false
            refreshLayout.isRefreshing = false
            if (mList?.size!! > 0) {
                mList?.clear()
            }
        }
        oneListBean.data.contentList?.forEach {
            mList?.add(it)
        }
        mAdapter?.notifyDataSetChanged()
    }

    override fun onRefresh() {
        if (!mIsRefresh) {
            mIsRefresh = true
            mPresenter?.start()
        }
    }

    override fun onResume() {
        super.onResume()
        //距离上次刷新超过6分钟，重新加载数据
        if (System.currentTimeMillis().minus(mLastRefreshTime) > 3600000) {
            mPresenter.start()
        }
        mLastRefreshTime = System.currentTimeMillis()
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