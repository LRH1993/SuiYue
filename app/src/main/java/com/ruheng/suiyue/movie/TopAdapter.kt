package com.ruheng.suiyue.movie

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.ruheng.suiyue.R
import com.ruheng.suiyue.data.bean.SubjectsItem
import me.zhanghai.android.materialratingbar.MaterialRatingBar

/**
 * Created by lvruheng on 2018/3/6.
 */
class TopAdapter(context: Context, movieList: MutableList<SubjectsItem>?) : RecyclerView.Adapter<TopAdapter.TopViewHolder>() {
    var context: Context? = context
    var list: MutableList<SubjectsItem>? = null
    var inflater: LayoutInflater? = null

    init {
        this.inflater = LayoutInflater.from(context)
        this.list = movieList
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        val bean = list?.get(position)
        val title = bean?.title
        val imgUrl = bean?.images?.small
        var rating = bean?.rating?.average
        val genres = bean?.genres
        val year = bean?.year
        var genreStr = "$year"
        val casts = bean?.casts
        var castStr = ""
        casts!!.indices
                .asSequence()
                .takeWhile { it <= 2 }
                .forEach { castStr = castStr + " / " + casts[it].name }
        if (castStr != "") {
            castStr = castStr.substring(3)
            holder?.tv_casts?.visibility = View.VISIBLE
            holder?.tv_casts?.text = castStr
        } else {
            holder?.tv_casts?.visibility = View.GONE
        }
        genres!!.indices
                .asSequence()
                .takeWhile { it <= 2 }
                .forEach { genreStr = genreStr + " / " + genres[it] }
        holder.tv_title?.text = title
        holder.sv_photo?.setImageURI(imgUrl)
        if (rating == 0.0) {
            holder?.rt_rating?.visibility = View.GONE
            holder?.tv_rating?.text = "暂无评分"
        } else {
            holder?.rt_rating?.visibility = View.VISIBLE
            holder?.rt_rating?.rating = rating?.toFloat()!! / 2
            holder?.tv_rating?.text = rating.toString()
        }
        holder?.tv_geners?.text = genreStr
        holder?.tv_casts?.text = castStr
        if (list?.size!! > 8) {
            holder?.tv_rank?.visibility = View.GONE
        } else {
            holder?.tv_rank?.visibility = View.VISIBLE
            holder?.tv_rank?.text = (position + 1).toString()
        }
        holder.ll_root?.setOnClickListener {
            var intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("id", bean?.id)
            context?.startActivity(intent)
        }
        holder.cv_root?.setOnClickListener {
            var intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("id", bean?.id)
            context?.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        return TopViewHolder(inflater?.inflate(R.layout.item_top_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    class TopViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var sv_photo: SimpleDraweeView? = null
        var tv_title: TextView? = null
        var tv_rating: TextView? = null
        var rt_rating: MaterialRatingBar? = null
        var tv_geners: TextView? = null
        var tv_casts: TextView? = null
        var tv_rank: TextView? = null
        var ll_root: LinearLayout? = null
        var cv_root: CardView? = null

        init {
            cv_root = itemView?.findViewById(R.id.cv_root)
            ll_root = itemView?.findViewById(R.id.ll_root)
            sv_photo = itemView?.findViewById(R.id.sv_top_photo)
            tv_title = itemView?.findViewById(R.id.tv_top_title)
            tv_rating = itemView?.findViewById(R.id.tv_top_rating)
            rt_rating = itemView?.findViewById(R.id.rt_top_rating)
            tv_geners = itemView?.findViewById(R.id.tv_top_geners)
            tv_casts = itemView?.findViewById(R.id.tv_top_casts)
            tv_rank = itemView?.findViewById(R.id.tv_top_rank)
        }
    }
}