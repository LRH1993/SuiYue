package com.ruheng.suiyue

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.gyf.barlibrary.ImmersionBar
import com.ruheng.suiyue.article.ArticleFragment
import com.ruheng.suiyue.base.BaseFragment
import com.ruheng.suiyue.book.BookFragment
import com.ruheng.suiyue.movie.MovieFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var articleFragment: BaseFragment
    private lateinit var bookFragment: BaseFragment
    private lateinit var movieFragment: BaseFragment
    private var mExitTime: Long = 0
    private var mToast: Toast? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ImmersionBar.with(this).transparentBar().barAlpha(0.3f).init()
        //隐藏底部导航栏
        val decorView = window.decorView
        val uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        decorView.systemUiVisibility = uiOptions
        initFragment(savedInstanceState)
        setRadioButton()

    }

    private fun setRadioButton() {
        rb_article.isChecked = true
        rb_article.setOnClickListener(this)
        rb_book.setOnClickListener(this)
        rb_movie.setOnClickListener(this)
    }

    private fun initFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
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
        } else {
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

    override fun onClick(v: View?) {
        clearState()
        when (v?.id) {
            R.id.rb_article -> {
                rb_article.isChecked = true
                rb_article.setCompoundDrawablesWithIntrinsicBounds(null, resources.getDrawable(R.drawable.icon_article), null, null)
                rb_article.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(articleFragment)
                        .hide(bookFragment)
                        .hide(movieFragment)
                        .commit()
                iv_search.visibility = View.INVISIBLE
                iv_scan.visibility = View.INVISIBLE
            }
            R.id.rb_book -> {
                rb_book.isChecked = true
                rb_book.setCompoundDrawablesWithIntrinsicBounds(null, resources.getDrawable(R.drawable.icon_book), null, null)
                rb_book.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(bookFragment)
                        .hide(articleFragment)
                        .hide(movieFragment)
                        .commit()
                iv_search.visibility = View.VISIBLE
                iv_scan.visibility = View.VISIBLE
            }
            R.id.rb_movie -> {
                rb_movie.isChecked = true
                rb_movie.setCompoundDrawablesWithIntrinsicBounds(null, resources.getDrawable(R.drawable.icon_movie), null, null)
                rb_movie.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(movieFragment)
                        .hide(articleFragment)
                        .hide(bookFragment)
                        .commit()
                iv_search.visibility = View.INVISIBLE
                iv_scan.visibility = View.INVISIBLE
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mToast?.let {
            mToast!!.cancel()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ImmersionBar.with(this).destroy()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 3000) {
                finish()
                mToast!!.cancel()
            } else {
                mExitTime = System.currentTimeMillis()
                mToast = Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT)
                mToast?.show()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun clearState() {
        rg_root.clearCheck()
        rb_article.setCompoundDrawablesWithIntrinsicBounds(null, resources.getDrawable(R.drawable.icon_article_gray), null, null)
        rb_book.setCompoundDrawablesWithIntrinsicBounds(null, resources.getDrawable(R.drawable.icon_book_gray), null, null)
        rb_movie.setCompoundDrawablesWithIntrinsicBounds(null, resources.getDrawable(R.drawable.icon_movie_gray), null, null)
        rb_article.setTextColor(resources.getColor(R.color.gray))
        rb_movie.setTextColor(resources.getColor(R.color.gray))
        rb_book.setTextColor(resources.getColor(R.color.gray))
    }
}
