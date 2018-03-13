package com.ruheng.suiyue.data.bean

import com.google.gson.annotations.SerializedName

data class MovieDetailBean(@SerializedName("year")
                           val year: String = "",
                           @SerializedName("directors")
                           val directors: List<DirectorsItem>?,
                           @SerializedName("rating")
                           val rating: MovieRating,
                           @SerializedName("douban_site")
                           val doubanSite: String = "",
                           @SerializedName("mobile_url")
                           val mobileUrl: String = "",
                           @SerializedName("title")
                           val title: String = "",
                           @SerializedName("schedule_url")
                           val scheduleUrl: String = "",
                           @SerializedName("subtype")
                           val subtype: String = "",
                           @SerializedName("genres")
                           val genres: List<String>?,
                           @SerializedName("id")
                           val id: String = "",
                           @SerializedName("ratings_count")
                           val ratingsCount: Int = 0,
                           @SerializedName("wish_count")
                           val wishCount: Int = 0,
                           @SerializedName("summary")
                           val summary: String = "",
                           @SerializedName("images")
                           val images: Images,
                           @SerializedName("original_title")
                           val originalTitle: String = "",
                           @SerializedName("alt")
                           val alt: String = "",
                           @SerializedName("countries")
                           val countries: List<String>?,
                           @SerializedName("collect_count")
                           val collectCount: Int = 0,
                           @SerializedName("casts")
                           val casts: List<Cast>?,
                           @SerializedName("share_url")
                           val shareUrl: String = "",
                           @SerializedName("comments_count")
                           val commentsCount: Int = 0,
                           @SerializedName("reviews_count")
                           val reviewsCount: Int = 0)