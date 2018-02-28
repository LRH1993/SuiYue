package com.ruheng.suiyue

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.ruheng.suiyue.article.ArticleFragment
import com.ruheng.suiyue.base.BaseFragment
import com.ruheng.suiyue.book.BookFragment
import com.ruheng.suiyue.movie.MovieFragment

class MainActivity : AppCompatActivity() {
    lateinit var articleFragment: BaseFragment
    lateinit var bookFragment: BaseFragment
    lateinit var movieFragment: BaseFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //隐藏底部导航栏
        val window = window
        val params = window.attributes
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        window.attributes = params
        initFragment(savedInstanceState)

    }

    private fun initFragment(savedInstanceState: Bundle?) {
        if(savedInstanceState!=null){
            //异常情况
            val mFragments: List<Fragment> = supportFragmentManager.fragments
            for (item in mFragments) {
                if (item is ArticleFragment) {
                    articleFragment = item
                }
                if (item is BookFragment) {
                    bookFragment = item
                }
                if (item is MovieFragment) {
                    movieFragment = item
                }
            }
        }else {
            articleFragment = ArticleFragment()
            bookFragment = BookFragment()
            movieFragment = MovieFragment()
            val fragmentTrans = supportFragmentManager.beginTransaction()
            fragmentTrans.add(R.id.fl_content, articleFragment)
            fragmentTrans.add(R.id.fl_content, bookFragment)
            fragmentTrans.add(R.id.fl_content, movieFragment)
            fragmentTrans.commit()
        }
        supportFragmentManager.beginTransaction().show(articleFragment)
                .hide(bookFragment)
                .hide(movieFragment)
                .commit()
    }

}
