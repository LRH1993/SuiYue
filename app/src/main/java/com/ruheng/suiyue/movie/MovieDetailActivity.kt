package com.ruheng.suiyue.movie

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gyf.barlibrary.ImmersionBar
import com.ruheng.suiyue.R
import com.ruheng.suiyue.data.bean.MovieDetailBean
import kotlinx.android.synthetic.main.activity_movie_detail.*

/**
 * Created by lvruheng on 2018/3/9.
 */
class MovieDetailActivity : AppCompatActivity(), MovieDetailContract.View {
    lateinit var mPresenter: MovieDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        ImmersionBar.with(this).barColor(R.color.detail_bg).barAlpha(0.3f).fitsSystemWindows(true).init()
    }

    override fun onResume() {
        super.onResume()
        mPresenter = MovieDetailPresenter(this)
        val extras = intent.extras
        val id = extras.get("id")
        mPresenter.start(id as String)
        iv_detail_back.setOnClickListener {
            finish()
        }
    }

    override fun setPresenter(presenter: MovieDetailContract.Presenter) {
        //忽略......
    }


    override fun getDetailContext(): Context? {
        return this
    }

    override fun setMovie(movieDetailBean: MovieDetailBean) {
        val title = movieDetailBean.title
        val originalTitle = movieDetailBean.originalTitle
        val rating = movieDetailBean.rating.average
        val count = movieDetailBean.collectCount
        val image = movieDetailBean.images?.large
        val summary = movieDetailBean.summary
        tv_movie_title.text = title
        tv_detail_title.text = title
        tv_origin_title.text = originalTitle
        tv_detail_count.text = "$count 人"
        tv_detail_rating.text = rating.toString()
        rt_detail_rating.rating = rating.toFloat() / 2
        sv_detail_photo.setImageURI(image)
        if (summary == "") {
            tv_detail_intro.text = "无"
        } else {
            tv_detail_intro.text = summary
        }

    }
}