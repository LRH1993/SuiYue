package com.ruheng.suiyue.article

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.ruheng.suiyue.R
import com.ruheng.suiyue.data.bean.ContentListItem

/**
 * Created by lvruheng on 2018/3/6.
 */
class ArticleAdapter(context: Context, contentList: MutableList<ContentListItem>?) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    var context: Context? = context
    var list: MutableList<ContentListItem>? = null
    var inflater: LayoutInflater? = null

    init {
        this.inflater = LayoutInflater.from(context)
        this.list = contentList
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        var bean = list?.get(position)
        val title = bean?.title
        val forward = bean?.forward
        val imgUrl = bean?.imgUrl
        val postDate = bean?.postDate
        val likeCount = bean?.likeCount
        holder.tv_desc?.text = forward
        holder.tv_like?.text = "喜欢 " + likeCount.toString()
        holder.tv_title?.text = title
        holder.sv_photo?.setImageURI(imgUrl)
        val time = postDate?.substring(5, 10)
        holder.tv_time?.text = time
        holder.cv_root?.setOnClickListener {
            //跳转到文章详情页
            var intent = Intent(context, ArticleDetailActivity::class.java)
            var item_id = bean?.itemId
            intent.putExtra("item_id", item_id)
            intent.putExtra("category",bean?.category)
            context?.let { context ->
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(inflater?.inflate(R.layout.item_article, parent, false))
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    class ArticleViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var sv_photo: SimpleDraweeView? = null
        var tv_title: TextView? = null
        var tv_desc: TextView? = null
        var tv_time: TextView? = null
        var tv_like: TextView? = null
        var cv_root: CardView? = null

        init {
            cv_root = itemView?.findViewById(R.id.cv_root)
            sv_photo = itemView?.findViewById(R.id.sv_article_photo)
            tv_title = itemView?.findViewById(R.id.tv_article_title)
            tv_desc = itemView?.findViewById(R.id.tv_article_desc)
            tv_time = itemView?.findViewById(R.id.tv_article_time)
            tv_like = itemView?.findViewById(R.id.tv_article_like)
        }
    }
}