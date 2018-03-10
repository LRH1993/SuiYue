package com.ruheng.suiyue.book

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ruheng.suiyue.R

/**
 * Created by lvruheng on 2018/3/6.
 */
class HotAdapter(context: Context, booksList: List<String>) : RecyclerView.Adapter<HotAdapter.HotViewHolder>() {
    var context: Context? = context
    var list: List<String>? = null
    var inflater: LayoutInflater? = null
    //热门搜索 固定假数据
    var ibsnList = listOf<String>("9787544270878", "9787536692930", "9787108009821", "9787544210966", "9787550013247", "9787108018809", "9787550263932", "9787539979410", "9789869236478")

    init {
        this.inflater = LayoutInflater.from(context)
        this.list = booksList
    }

    override fun onBindViewHolder(holder: HotViewHolder, position: Int) {
        val bean = list?.get(position)
        holder?.tv_book_rank?.text = (position + 1).toString()
        holder?.tv_book_name?.text = bean
        holder?.itemView?.setOnClickListener {
            //跳转到书籍详情页面
            var intent = Intent(context, BookDetailActivity::class.java)
            intent.putExtra("ibsn", ibsnList[position])
            context?.startActivity(intent)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotViewHolder {
        return HotViewHolder(inflater?.inflate(R.layout.item_search_hot, parent, false))
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    class HotViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var tv_book_name: TextView? = null
        var tv_book_rank: TextView? = null

        init {

            tv_book_name = itemView?.findViewById(R.id.tv_book_name)
            tv_book_rank = itemView?.findViewById(R.id.tv_book_rank)
        }
    }
}