package com.ruheng.suiyue.movie

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.ruheng.suiyue.R
import com.ruheng.suiyue.data.bean.SubjectsItem
import me.zhanghai.android.materialratingbar.MaterialRatingBar

/**
 * Created by lvruheng on 2018/3/6.
 */
class OnlineAdapter(context: Context, movieList: MutableList<SubjectsItem>?) : RecyclerView.Adapter<OnlineAdapter.OnlineViewHolder>() {
    var context: Context? = context
    var list: MutableList<SubjectsItem>? = null
    var inflater: LayoutInflater? = null

    init {
        this.inflater = LayoutInflater.from(context)
        this.list = movieList
    }

    override fun onBindViewHolder(holder: OnlineViewHolder, position: Int) {
        val bean = list?.get(position)
        val title = bean?.title
        val imgUrl = bean?.images?.small
        var rating = bean?.rating?.average
        if(rating==0.0){
            rating=8.5
        }
        holder.tv_title?.text = title
        holder.sv_photo?.setImageURI(imgUrl)
        holder?.rt_rating?.rating = rating?.toFloat()!! / 2
        holder?.tv_rating?.text = rating.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnlineViewHolder {
        return OnlineViewHolder(inflater?.inflate(R.layout.item_online_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    class OnlineViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var sv_photo: SimpleDraweeView? = null
        var tv_title: TextView? = null
        var tv_rating: TextView? = null
        var rt_rating: MaterialRatingBar? = null

        init {
            sv_photo = itemView?.findViewById(R.id.sv_movie_photo)
            tv_title = itemView?.findViewById(R.id.tv_movie_title)
            tv_rating = itemView?.findViewById(R.id.tv_movie_rating)
            rt_rating = itemView?.findViewById(R.id.rt_movie_rating)
        }
    }
}