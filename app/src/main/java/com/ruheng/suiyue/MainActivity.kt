package com.ruheng.suiyue

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import com.gyf.barlibrary.ImmersionBar
import com.ruheng.suiyue.article.ArticleFragment
import com.ruheng.suiyue.article.ArticlePresenter
import com.ruheng.suiyue.book.*
import com.ruheng.suiyue.data.bean.Weather
import com.ruheng.suiyue.movie.MovieFragment
import com.ruheng.suiyue.movie.MoviePresenter
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var articleFragment: ArticleFragment
    private lateinit var bookFragment: BookFragment
    private lateinit var movieFragment: MovieFragment
    private var searchFragment: SearchFragment? = null
    private lateinit var bookPresenter: BookPresenter
    private lateinit var moviePresenter: MoviePresenter
    private lateinit var articlePresenter: ArticlePresenter
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
        setBarClick()
        EventBus.getDefault().register(this)

    }

    private fun setBarClick() {
        iv_search.setOnClickListener {
            if (iv_search.visibility == View.VISIBLE) {
                if (searchFragment == null) {
                    searchFragment = SearchFragment()
                }
                searchFragment?.show(fragmentManager, SEARCH_TAG)
            }
        }
        var integrator = IntentIntegrator(this)
        integrator.setOrientationLocked(false)
        iv_scan.setOnClickListener {
            if (iv_scan.visibility == View.VISIBLE) {
                integrator.initiateScan()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val parseActivityResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (parseActivityResult != null) {
            if (parseActivityResult.contents != null) {
                var intent = Intent(this, BookDetailActivity::class.java)
                intent.putExtra("ibsn", parseActivityResult.contents)
                startActivity(intent)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)

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
        bookPresenter = BookPresenter(bookFragment)
        moviePresenter = MoviePresenter(movieFragment)
        articlePresenter = ArticlePresenter(articleFragment)
        supportFragmentManager.beginTransaction().show(articleFragment)
                .hide(bookFragment)
                .hide(movieFragment)
                .commit()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onWeatherEvent(weather: Weather) {
        val cityName = weather.cityName
        val climate = weather.climate
        val temperature = weather.temperature
        tv_bar_weather.text = "$cityName·$climate  $temperature"
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
        EventBus.getDefault().unregister(this)
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
