package com.ruheng.suiyue.book

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gyf.barlibrary.ImmersionBar
import com.ruheng.suiyue.R
import com.ruheng.suiyue.data.bean.BooksItem
import kotlinx.android.synthetic.main.activity_book_detail.*

/**
 * Created by lvruheng on 2018/3/9.
 */
class BookDetailActivity : AppCompatActivity(), BookDetailContract.View {
    lateinit var mPresenter: BookDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        ImmersionBar.with(this).barColor(R.color.detail_bg).barAlpha(0.3f).fitsSystemWindows(true).init()
    }

    override fun onResume() {
        super.onResume()
        mPresenter = BookDetailPresenter(this)
        val extras = intent.extras
        val ibsn = extras.get("ibsn")
        if(ibsn==null){
            finish()
        }
        mPresenter.start(ibsn as String)
        iv_detail_back.setOnClickListener {
            finish()
        }
    }

    override fun setPresenter(presenter: BookDetailContract.Presenter) {
        //忽略......
    }


    override fun getDetailContext(): Context? {
        return this
    }

    override fun setBook(booksItem: BooksItem) {
        val title = booksItem.title
        val author = booksItem.author.toString().replace("[", "").replace("]", "")
        val rating = booksItem.rating.average
        val count = booksItem.rating.numRaters
        val image = booksItem.images?.large
        val publisher = booksItem.publisher
        val pubdate = booksItem.pubdate
        val summary = booksItem.summary
        tv_book_title.text = title
        tv_detail_title.text = title
        tv_detail_author.text = "作者：$author"
        tv_detail_publish.text = "出版社：$publisher"
        tv_detail_data.text = "出版日期：$pubdate"
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
