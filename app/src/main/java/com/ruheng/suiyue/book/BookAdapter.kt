package com.ruheng.suiyue.book

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
import com.ruheng.suiyue.data.bean.BooksItem
import me.zhanghai.android.materialratingbar.MaterialRatingBar

/**
 * Created by lvruheng on 2018/3/6.
 */
class BookAdapter(context: Context, booksList: MutableList<BooksItem>?) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    var context: Context? = context
    var list: MutableList<BooksItem>? = null
    var inflater: LayoutInflater? = null

    init {
        this.inflater = LayoutInflater.from(context)
        this.list = booksList
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val bean = list?.get(position)
        val title = bean?.title
        val image = bean?.image
        val price = "￥" + bean?.price?.replace("元", "", false)
        val subtitle = bean?.subtitle
        val rating = bean?.rating?.average
        val rating_count = bean?.rating?.numRaters
        val desc = bean?.summary
        holder.tv_title?.text = title
        holder.tv_desc?.text = desc
        if (price == "￥") {
            holder.tv_price?.text = "价格不详"
        } else {
            holder.tv_price?.text = price
        }

        holder.tv_rating_count?.text = "$rating_count 评价"
        if (subtitle == "") {
            holder.tv_subtitle?.visibility = View.GONE
        } else {
            holder.tv_subtitle?.text = subtitle
        }
        holder.rt_rating?.rating = rating?.toFloat()!! / 2
        holder.tv_rating?.text = rating
        holder.sv_photo?.setImageURI(image)
        holder.cv_root?.setOnClickListener {
            var intent = Intent(context, BookDetailActivity::class.java)
            intent.putExtra("ibsn", bean?.isbn13)
            context?.startActivity(intent)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(inflater?.inflate(R.layout.item_book, parent, false))
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    class BookViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var sv_photo: SimpleDraweeView? = null
        var tv_title: TextView? = null
        var tv_desc: TextView? = null
        var tv_price: TextView? = null
        var tv_subtitle: TextView? = null
        var cv_root: CardView? = null
        var tv_rating: TextView? = null
        var tv_rating_count: TextView? = null
        var rt_rating: MaterialRatingBar? = null


        init {
            cv_root = itemView?.findViewById(R.id.cv_root)
            sv_photo = itemView?.findViewById(R.id.sv_book_photo)
            tv_title = itemView?.findViewById(R.id.tv_book_title)
            tv_desc = itemView?.findViewById(R.id.tv_book_desc)
            tv_price = itemView?.findViewById(R.id.tv_book_price)
            tv_subtitle = itemView?.findViewById(R.id.tv_book_subtitle)
            rt_rating = itemView?.findViewById(R.id.rt_book_rating)
            tv_rating = itemView?.findViewById(R.id.tv_rating)
            tv_rating_count = itemView?.findViewById(R.id.tv_rating_count)
        }
    }
}