package com.ruheng.suiyue.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.ruheng.suiyue.R
import com.ruheng.suiyue.base.BaseFragment
import com.ruheng.suiyue.data.bean.MovieListBean
import com.ruheng.suiyue.data.bean.SubjectsItem
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * Created by lvruheng on 2018/2/28.
 */
class MovieFragment : BaseFragment(), MovieContract.View, View.OnClickListener {

    lateinit var mPresenter: MoviePresenter
    var mLastRefreshTime: Long = 0
    var mOnlineAdapter: OnlineAdapter? = null
    var mComingAdapter: ComingAdapter? = null
    var mTopAdapter: TopAdapter? = null
    var mOnlineList = ArrayList<SubjectsItem>()
    var mComingList = ArrayList<SubjectsItem>()
    var mTopList = ArrayList<SubjectsItem>()
    var mAllOnlineList = ArrayList<SubjectsItem>()
    var mAllComingList = ArrayList<SubjectsItem>()
    var mAllTopList = ArrayList<SubjectsItem>()
    var mOnlineCount: Int = 0
    var mComingCount: Int = 0
    var mTopCount: Int = 0
    override fun getLayoutResources(): Int {
        return R.layout.fragment_movie
    }

    override fun initView(savedInstanceState: Bundle?) {
        tv_coming_all.setOnClickListener(this)
        tv_online_all.setOnClickListener(this)
        tv_top_all.setOnClickListener(this)
        var onlineLayoutManager = LinearLayoutManager(context)
        onlineLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        rv_online.layoutManager = onlineLayoutManager
        mOnlineAdapter = OnlineAdapter(context!!, mOnlineList)
        rv_online.adapter = mOnlineAdapter
        var comingLayoutManager = LinearLayoutManager(context)
        comingLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        rv_coming.layoutManager = comingLayoutManager
        mComingAdapter = ComingAdapter(context!!, mComingList)
        rv_coming.adapter = mComingAdapter
        var topLayoutManager = LinearLayoutManager(context)
        rv_top.layoutManager = topLayoutManager
        mTopAdapter = TopAdapter(context!!, mTopList)
        rv_top.adapter = mTopAdapter
        //todo 滑动冲突
    }

    override fun setOnlineList(movieListBean: MovieListBean) {
        if (mOnlineList?.size!! > 0) {
            mOnlineList?.clear()
        }
        mOnlineCount = movieListBean.total
        movieListBean.subjects?.forEach {
            //只展示6个上映电影
            if (mOnlineList.size < 6) {
                mOnlineList?.add(it)
            }
            mAllOnlineList?.add(it)
        }
        mOnlineAdapter?.notifyDataSetChanged()
    }

    override fun setComingList(movieListBean: MovieListBean) {
        if (mComingList?.size!! > 0) {
            mComingList?.clear()
        }
        mComingCount = movieListBean.total
        movieListBean.subjects?.forEach {
            //只展示8个即将上映电影
            if (mComingList.size < 8) {
                mComingList?.add(it)
            }
            mAllComingList?.add(it)
        }
        mComingAdapter?.notifyDataSetChanged()
    }

    override fun setTopList(movieListBean: MovieListBean) {
        if (mTopList?.size!! > 0) {
            mTopList?.clear()
        }
        mTopCount = movieListBean.total
        movieListBean.subjects?.forEach {
            //只展示5个Top 250电影
            if (mTopList.size < 5) {
                mTopList?.add(it)
            }
            mAllTopList?.add(it)
        }
        mTopAdapter?.notifyDataSetChanged()
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

    override fun onClick(view: View?) {
        var intent = Intent(activity, MovieListActivity::class.java)
        when (view?.id) {

            R.id.tv_online_all -> {
                intent.putExtra("title", "影院热映")
                intent.putExtra("list", mAllOnlineList)
                intent.putExtra("count", mOnlineCount)
            }

            R.id.tv_coming_all -> {
                intent.putExtra("title", "院线即将上映")
                intent.putExtra("list", mAllComingList)
                intent.putExtra("count", mComingCount)
            }

            R.id.tv_top_all -> {
                intent.putExtra("title", "豆瓣 Top250")
                intent.putExtra("list", mAllTopList)
                intent.putExtra("count", mTopCount)
            }

        }
        context?.startActivity(intent)

    }

    override fun isActive(): Boolean {
        return isAdded
    }

    override fun getMovieContext(): Context? {
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