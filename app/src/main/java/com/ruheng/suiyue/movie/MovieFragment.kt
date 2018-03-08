package com.ruheng.suiyue.movie

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.ruheng.suiyue.R
import com.ruheng.suiyue.base.BaseFragment
import com.ruheng.suiyue.data.bean.MovieListBean
import com.ruheng.suiyue.data.bean.SubjectsItem
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * Created by lvruheng on 2018/2/28.
 */
class MovieFragment : BaseFragment(), MovieContract.View {

    lateinit var mPresenter: MoviePresenter
    var mLastRefreshTime: Long = 0
    var mAdapter: OnlineAdapter? = null
    var mOnlineList = ArrayList<SubjectsItem>()
    override fun getLayoutResources(): Int {
        return R.layout.fragment_movie
    }

    override fun initView(savedInstanceState: Bundle?) {
        var layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        rv_online.layoutManager = layoutManager
        mAdapter = OnlineAdapter(context!!, mOnlineList)
        rv_online.adapter = mAdapter
    }

    override fun onResume() {
        super.onResume()
        //距离上次刷新超过6分钟，重新加载数据
        if (System.currentTimeMillis().minus(mLastRefreshTime) > 3600000) {
            mPresenter.start()
        }
        mLastRefreshTime = System.currentTimeMillis()
    }

    override fun setOnlineList(movieListBean: MovieListBean) {
        if (mOnlineList?.size!! > 0) {
            mOnlineList?.clear()
        }
        movieListBean.subjects?.forEach {
            //只展示6个上映电影
            if (mOnlineList.size < 6) {
                mOnlineList?.add(it)
            }
        }
        mAdapter?.notifyDataSetChanged()
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