package com.ruheng.suiyue.movie

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.gyf.barlibrary.ImmersionBar
import com.ruheng.suiyue.R
import com.ruheng.suiyue.data.bean.MovieListBean
import com.ruheng.suiyue.data.bean.SubjectsItem
import kotlinx.android.synthetic.main.activity_movie_list.*

/**
 * Created by lvruheng on 2018/3/13.
 */
class MovieListActivity : AppCompatActivity(), MovieListContract.View {
    private var mStart: Int = 20
    private lateinit var mPresenter: MovieListPresenter
    private var mAdapter: TopAdapter? = null
    private var mList = ArrayList<SubjectsItem>()
    private var mType: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        ImmersionBar.with(this).transparentBar().barAlpha(0.3f).fitsSystemWindows(true).init()
        val extras = intent.extras
        val title = extras.get("title")
        val count = extras.get("count") as Int
        mType = when (title) {
            "影院热映" -> 1
            "院线即将上映" -> 2
            else -> 3
        }
        tv_movie_list_title.text = title as CharSequence?
        mList = extras.get("list") as ArrayList<SubjectsItem>
        var topLayoutManager = LinearLayoutManager(this)
        rv_movie_list.layoutManager = topLayoutManager
        mAdapter = TopAdapter(this, mList)
        rv_movie_list.adapter = mAdapter
        rv_movie_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                var layoutManager: LinearLayoutManager = recyclerView?.layoutManager as LinearLayoutManager
                var lastPositon = layoutManager.findLastVisibleItemPosition()
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPositon == mList.size - 1) {
                    //加载更多
                    if (mStart < count) {
                        mPresenter?.loadData(mStart, mType)
                        mStart += 20
                    }
                }
            }
        })
        iv_movie_back.setOnClickListener{
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        mPresenter = MovieListPresenter(this)
        mPresenter.start()
    }

    override fun setPresenter(presenter: MovieListContract.Presenter) {
        //忽略......
    }


    override fun getMovieListContext(): Context? {
        return this
    }

    override fun setList(movieListBean: MovieListBean) {
        movieListBean.subjects?.forEach {
            mList.add(it)
        }
        mAdapter?.notifyDataSetChanged()
    }
}