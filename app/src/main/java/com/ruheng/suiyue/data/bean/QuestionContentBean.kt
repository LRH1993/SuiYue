package com.ruheng.suiyue.data.bean

import com.google.gson.annotations.SerializedName

/**
 * Created by lvruheng on 2018/3/14.
 */
data class QuestionContentBean(@SerializedName("res")
                             val res: Int = 0,
                               @SerializedName("data")
                             val data: QuestionBean)