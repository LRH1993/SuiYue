package com.ruheng.suiyue.book

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ruheng.suiyue.R

/**
 * Created by lvruheng on 2018/3/6.
 */
class HistoryAdapter(context: Context, booksList: List<String>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    var context: Context? = context
    var list: List<String>? = null
    var inflater: LayoutInflater? = null

    init {
        this.inflater = LayoutInflater.from(context)
        this.list = booksList
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val bean = list?.get(position)
        holder?.tv_book_name?.text = bean

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(inflater?.inflate(R.layout.item_search_history, parent, false))
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    class HistoryViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var tv_book_name: TextView? = null

        init {

            tv_book_name = itemView?.findViewById(R.id.tv_book_name)
        }
    }
}