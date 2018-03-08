package com.ruheng.suiyue.data.bean

import com.google.gson.annotations.SerializedName

data class SubjectsItem(@SerializedName("images")
                        val images: Images,
                        @SerializedName("original_title")
                        val originalTitle: String = "",
                        @SerializedName("subtype")
                        val subtype: String = "",
                        @SerializedName("year")
                        val year: String = "",
                        @SerializedName("genres")
                        val genres: List<String>?,
                        @SerializedName("rating")
                        val rating: MovieRating,
                        @SerializedName("alt")
                        val alt: String = "",
                        @SerializedName("id")
                        val id: String = "",
                        @SerializedName("title")
                        val title: String = "",
                        @SerializedName("collect_count")
                        val collectCount: Int = 0)