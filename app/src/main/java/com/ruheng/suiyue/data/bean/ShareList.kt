package com.ruheng.suiyue.data.bean

import com.google.gson.annotations.SerializedName

data class ShareList(@SerializedName("qq")
                     val qq: Qq,
                     @SerializedName("wx")
                     val wx: Wx,
                     @SerializedName("weibo")
                     val weibo: Weibo,
                     @SerializedName("wx_timeline")
                     val wxTimeline: WxTimeline)