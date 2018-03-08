package com.ruheng.suiyue.article

import android.content.Context
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.ruheng.suiyue.R
import com.ruheng.suiyue.base.BaseFragment
import com.ruheng.suiyue.data.bean.ContentListItem
import com.ruheng.suiyue.data.bean.OneListBean
import kotlinx.android.synthetic.main.fragment_article.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by lvruheng on 2018/2/28.
 */
class ArticleFragment : BaseFragment(), ArticleContract.View, SwipeRefreshLayout.OnRefreshListener {

    lateinit var mPresenter: ArticlePresenter
    var mLastRefreshTime: Long = 0
    var mIsRefresh: Boolean = false
    private var mIndex: Int = 1
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
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                var layoutManager: LinearLayoutManager = recyclerView?.layoutManager as LinearLayoutManager
                var lastPositon = layoutManager.findLastVisibleItemPosition()
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPositon == mList.size - 1) {
                    if (mIndex!! < 10) {
                        mPresenter?.loadMore(mIndex!!)
                    }

                }
            }
        })
    }


    override fun setOneList(oneListBean: OneListBean) {
        if (mList.size == 0) {
            EventBus.getDefault().post(oneListBean.data.weather)
        }
        if (mIsRefresh) {
            mIsRefresh = false
            refreshLayout.isRefreshing = false
            if (mList?.size!! > 0) {
                mList?.clear()
            }
            mIndex = 1
            EventBus.getDefault().post(oneListBean.data.weather)
        } else if (mList?.size > 0) {
            mIndex = mIndex.inc()
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
            if (mList.size > 0) {
                mList.clear()
            }
        }
        mLastRefreshTime = System.currentTimeMillis()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            //距离上次刷新超过6分钟，重新加载数据
            if (System.currentTimeMillis().minus(mLastRefreshTime) > 3600000) {
                mPresenter.start()
                if (mList.size > 0) {
                    mList.clear()
                }
            }
            mLastRefreshTime = System.currentTimeMillis()
        }
    }

    override fun setPresenter(presenter: ArticleContract.Presenter) {
        mPresenter = presenter as ArticlePresenter
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