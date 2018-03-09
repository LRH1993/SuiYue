package com.ruheng.suiyue.data.bean

import com.google.gson.annotations.SerializedName

data class BooksItem(@SerializedName("origin_title")
                     val originTitle: String = "",
                     @SerializedName("summary")
                     val summary: String = "",
                     @SerializedName("image")
                     val image: String = "",
                     @SerializedName("images")
                     val images: Images?,
                     @SerializedName("author")
                     val author: List<String>?,
                     @SerializedName("catalog")
                     val catalog: String = "",
                     @SerializedName("rating")
                     val rating: Rating,
                     @SerializedName("alt")
                     val alt: String = "",
                     @SerializedName("binding")
                     val binding: String = "",
                     @SerializedName("title")
                     val title: String = "",
                     @SerializedName("url")
                     val url: String = "",
                     @SerializedName("alt_title")
                     val altTitle: String = "",
                     @SerializedName("author_intro")
                     val authorIntro: String = "",
                     @SerializedName("pages")
                     val pages: String = "",
                     @SerializedName("price")
                     val price: String = "",
                     @SerializedName("subtitle")
                     val subtitle: String = "",
                     @SerializedName("isbn13")
                     val isbn13: String = "",
                     @SerializedName("publisher")
                     val publisher: String = "",
                     @SerializedName("isbn10")
                     val isbn10: String = "",
                     @SerializedName("id")
                     val id: String = "",
                     @SerializedName("pubdate")
                     val pubdate: String = "")