package com.ruheng.suiyue.data.bean

import com.google.gson.annotations.SerializedName

data class ArticleData(@SerializedName("hp_makettime")
                       val hpMakettime: String = "",
                       @SerializedName("copyright")
                       val copyright: String = "",
                       @SerializedName("audio_duration")
                       val audioDuration: String = "",
                       @SerializedName("maketime")
                       val maketime: String = "",
                       @SerializedName("guide_word")
                       val guideWord: String = "",
                       @SerializedName("hp_title")
                       val hpTitle: String = "",
                       @SerializedName("start_video")
                       val startVideo: String = "",
                       @SerializedName("hp_author")
                       val hpAuthor: String = "",
                       @SerializedName("hp_content")
                       val hpContent: String = "",
                       @SerializedName("top_media_image")
                       val topMediaImage: String = "",
                       @SerializedName("cover")
                       val cover: String = "",
                       @SerializedName("wb_name")
                       val wbName: String = "",
                       @SerializedName("hide_flag")
                       val hideFlag: String = "",
                       @SerializedName("wb_img_url")
                       val wbImgUrl: String = "",
                       @SerializedName("auth_it")
                       val authIt: String = "",
                       @SerializedName("top_media_type")
                       val topMediaType: String = "",
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
                       @SerializedName("sub_title")
                       val subTitle: String = "",
                       @SerializedName("content_id")
                       val contentId: String = "",
                       @SerializedName("author")
                       val author: List<AuthorItem>?,
                       @SerializedName("previous_id")
                       val previousId: String = "",
                       @SerializedName("praisenum")
                       val praisenum: Int = 0,
                       @SerializedName("author_list")
                       val authorList: List<AuthorListItem>?,
                       @SerializedName("sharenum")
                       val sharenum: Int = 0,
                       @SerializedName("hp_author_introduce")
                       val hpAuthorIntroduce: String = "",
                       @SerializedName("web_url")
                       val webUrl: String = "",
                       @SerializedName("anchor")
                       val anchor: String = "",
                       @SerializedName("next_id")
                       val nextId: Int = 0)