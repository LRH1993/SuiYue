package com.ruheng.suiyue.data.bean

import com.google.gson.annotations.SerializedName

data class SerialBean(@SerializedName("copyright")
                      val copyright: String = "",
                      @SerializedName("maketime")
                      val maketime: String = "",
                      @SerializedName("audio_duration")
                      val audioDuration: String = "",
                      @SerializedName("input_name")
                      val inputName: String = "",
                      @SerializedName("start_video")
                      val startVideo: String = "",
                      @SerializedName("title")
                      val title: String = "",
                      @SerializedName("content")
                      val content: String = "",
                      @SerializedName("top_media_image")
                      val topMediaImage: String = "",
                      @SerializedName("cover")
                      val cover: String = "",
                      @SerializedName("number")
                      val number: String = "",
                      @SerializedName("read_num")
                      val readNum: String = "",
                      @SerializedName("hide_flag")
                      val hideFlag: String = "",
                      @SerializedName("serial_id")
                      val serialId: String = "",
                      @SerializedName("top_media_type")
                      val topMediaType: String = "",
                      @SerializedName("id")
                      val id: String = "",
                      @SerializedName("audio")
                      val audio: String = "",
                      @SerializedName("top_media_file")
                      val topMediaFile: String = "",
                      @SerializedName("last_update_date")
                      val lastUpdateDate: String = "",
                      @SerializedName("editor_email")
                      val editorEmail: String = "",
                      @SerializedName("commentnum")
                      val commentnum: Int = 0,
                      @SerializedName("charge_edt")
                      val chargeEdt: String = "",
                      @SerializedName("author")
                      val author: AuthorItem,
                      @SerializedName("lastid")
                      val lastid: String = "",
                      @SerializedName("praisenum")
                      val praisenum: Int = 0,
                      @SerializedName("author_list")
                      val authorList: List<AuthorListItem>?,
                      @SerializedName("sharenum")
                      val sharenum: Int = 0,
                      @SerializedName("last_update_name")
                      val lastUpdateName: String = "",
                      @SerializedName("nextid")
                      val nextid: Int = 0,
                      @SerializedName("web_url")
                      val webUrl: String = "",
                      @SerializedName("anchor")
                      val anchor: String = "",
                      @SerializedName("excerpt")
                      val excerpt: String = "")