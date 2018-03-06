package com.ruheng.suiyue.data.bean

import com.google.gson.annotations.SerializedName

data class ListItem(@SerializedName("content_type")
                    val contentType: String = "",
                    @SerializedName("content_id")
                    val contentId: String = "",
                    @SerializedName("tag")
                    val tag: Tag,
                    @SerializedName("title")
                    val title: String = "")