package com.ruheng.suiyue.book

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.ruheng.suiyue.R

/**
 * Created by lvruheng on 2018/3/9.
 */
class BookDetailActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_book_detail)
    }
}