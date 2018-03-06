package com.ruheng.suiyue.article

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ruheng.suiyue.R
import com.ruheng.suiyue.data.bean.ContentListItem

/**
 * Created by lvruheng on 2018/3/6.
 */
class ArticleAdapter(context: Context, contentList: MutableList<ContentListItem>?) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    var context: Context? = context
    var list: MutableList<ContentListItem>? = contentList
    var inflater: LayoutInflater? = null

    init {
        this.inflater = LayoutInflater.from(context)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(inflater?.inflate(R.layout.item_article, parent, false), context!!)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    class ArticleViewHolder(itemView: View?, context: Context) : RecyclerView.ViewHolder(itemView) {

    }
}