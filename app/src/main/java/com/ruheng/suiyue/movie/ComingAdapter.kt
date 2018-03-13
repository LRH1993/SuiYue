package com.ruheng.suiyue.movie

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
import com.ruheng.suiyue.data.bean.SubjectsItem

/**
 * Created by lvruheng on 2018/3/6.
 */
class ComingAdapter(context: Context, movieList: MutableList<SubjectsItem>?) : RecyclerView.Adapter<ComingAdapter.ComingViewHolder>() {
    var context: Context? = context
    var list: MutableList<SubjectsItem>? = null
    var inflater: LayoutInflater? = null

    init {
        this.inflater = LayoutInflater.from(context)
        this.list = movieList
    }

    override fun onBindViewHolder(holder: ComingViewHolder, position: Int) {
        val bean = list?.get(position)
        val title = bean?.title
        val imgUrl = bean?.images?.small
        val genres = bean?.genres
        var genStr = if (genres?.size!! >= 2) {
            "${genres?.get(0)} / ${genres?.get(1)}"
        } else {
            genres[0]
        }
        holder.tv_title?.text = title
        holder.sv_photo?.setImageURI(imgUrl)
        holder.tv_geners?.text = genStr
        holder.cv_root?.setOnClickListener {
            var intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("id", bean.id)
            context?.startActivity(intent)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComingViewHolder {
        return ComingViewHolder(inflater?.inflate(R.layout.item_coming_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    class ComingViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var sv_photo: SimpleDraweeView? = null
        var tv_title: TextView? = null
        var tv_geners: TextView? = null
        var cv_root: CardView? = null

        init {
            cv_root = itemView?.findViewById(R.id.cv_root)
            sv_photo = itemView?.findViewById(R.id.sv_movie_photo)
            tv_title = itemView?.findViewById(R.id.tv_movie_title)
            tv_geners = itemView?.findViewById(R.id.tv_movie_geners)
        }
    }
}