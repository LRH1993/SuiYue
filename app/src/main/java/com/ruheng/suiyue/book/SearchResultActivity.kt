package com.ruheng.suiyue.book

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.gyf.barlibrary.ImmersionBar
import com.ruheng.suiyue.R
import com.ruheng.suiyue.data.bean.BookListBean
import com.ruheng.suiyue.data.bean.BooksItem
import kotlinx.android.synthetic.main.activity_search.*

/**
 * Created by lvruheng on 2018/3/9.
 */
class SearchResultActivity : AppCompatActivity(), SearchResultContract.View {
    lateinit var mPresenter: SearchResultPresenter
    var mList = ArrayList<BooksItem>()
    private var mAdapter: BookAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        ImmersionBar.with(this).transparentBar().barAlpha(0.3f).fitsSystemWindows(true).init()

    }

    override fun onResume() {
        super.onResume()
        mPresenter = SearchResultPresenter(this)
        val extras = intent.extras
        val keyword = extras.get("keyWord")
        mPresenter.start(keyword as String)
        tv_search_keyword.text = keyword
        iv_search_back.setOnClickListener {
            finish()
        }
        rv_search.layoutManager = LinearLayoutManager(this)
        mAdapter = BookAdapter(this, mList)
        rv_search.adapter = mAdapter
    }

    override fun setBookList(bookListBean: BookListBean) {
        if (mList?.size!! > 0) {
            mList?.clear()
        }
        bookListBean.books?.forEach {
            mList?.add(it)
        }
        tv_search_result.text = "共${mList.size}个搜索结果"
        mAdapter?.notifyDataSetChanged()
    }

    override fun setPresenter(presenter: SearchResultContract.Presenter) {
        //忽略......
    }

    override fun getSearchContext(): Context? {
        return this
    }

}