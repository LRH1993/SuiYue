package com.ruheng.suiyue.movie

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.gyf.barlibrary.ImmersionBar
import com.ruheng.suiyue.R
import com.ruheng.suiyue.data.bean.SubjectsItem
import kotlinx.android.synthetic.main.activity_movie_list.*

/**
 * Created by lvruheng on 2018/3/13.
 */
class MovieListActivity : AppCompatActivity() {
    private var mTopAdapter: TopAdapter? = null
    private var mList = ArrayList<SubjectsItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        ImmersionBar.with(this).transparentBar().barAlpha(0.3f).fitsSystemWindows(true).init()
        val extras = intent.extras
        val title = extras.get("title")
        tv_movie_list_title.text = title as CharSequence?
        mList = extras.get("list") as ArrayList<SubjectsItem>
        var topLayoutManager = LinearLayoutManager(this)
        rv_movie_list.layoutManager = topLayoutManager
        mTopAdapter = TopAdapter(this, mList)
        rv_movie_list.adapter = mTopAdapter
    }
}