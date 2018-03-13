package com.ruheng.suiyue.movie

import android.content.Context
import com.ruheng.suiyue.base.BasePresenter
import com.ruheng.suiyue.base.BaseView
import com.ruheng.suiyue.data.bean.MovieListBean

/**
 * Created by lvruheng on 2018/3/1.
 */
interface MovieListContract {
    interface View : BaseView<Presenter> {
        fun getMovieListContext(): Context?
        fun setList(movieListBean: MovieListBean)
    }

    interface Presenter : BasePresenter {
        fun loadData(start: Int,type: Int)
    }
}