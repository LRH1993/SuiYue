package com.ruheng.suiyue.movie

import android.content.Context
import com.ruheng.suiyue.base.BasePresenter
import com.ruheng.suiyue.base.BaseView
import com.ruheng.suiyue.data.bean.MovieDetailBean

/**
 * Created by lvruheng on 2018/3/1.
 */
interface MovieDetailContract {
    interface View : BaseView<Presenter> {
        fun getDetailContext(): Context?
        fun setMovie(movieDetailBean: MovieDetailBean)
    }

    interface Presenter : BasePresenter {
        fun start(id: String)
        fun loadData(id: String)
    }
}