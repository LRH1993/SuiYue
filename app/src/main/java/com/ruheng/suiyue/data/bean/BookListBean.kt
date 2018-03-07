package com.ruheng.suiyue.data.bean

import com.google.gson.annotations.SerializedName

data class BookListBean(@SerializedName("total")
                        val total: Int = 0,
                        @SerializedName("books")
                        val books: List<BooksItem>?,
                        @SerializedName("count")
                        val count: Int = 0,
                        @SerializedName("start")
                        val start: Int = 0)