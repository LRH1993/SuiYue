package com.ruheng.suiyue.data.bean

import com.google.gson.annotations.SerializedName

data class IdListBean(@SerializedName("res")
                      val res: Int = 0,
                      @SerializedName("data")
                      val data: List<String>?)