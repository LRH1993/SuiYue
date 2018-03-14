package com.ruheng.suiyue.data.bean

import com.google.gson.annotations.SerializedName

data class QuestionBean(@SerializedName("content_bgcolor")
                        val contentBgcolor: String = "",
                        @SerializedName("recommend_flag")
                        val recommendFlag: String = "",
                        @SerializedName("copyright")
                        val copyright: String = "",
                        @SerializedName("audio_duration")
                        val audioDuration: String = "",
                        @SerializedName("guide_word")
                        val guideWord: String = "",
                        @SerializedName("start_video")
                        val startVideo: String = "",
                        @SerializedName("question_title")
                        val questionTitle: String = "",
                        @SerializedName("cover")
                        val cover: String = "",
                        @SerializedName("read_num")
                        val readNum: String = "",
                        @SerializedName("answerer")
                        val answerer: Answerer,
                        @SerializedName("answer_content")
                        val answerContent: String = "",
                        @SerializedName("audio")
                        val audio: String = "",
                        @SerializedName("last_update_date")
                        val lastUpdateDate: String = "",
                        @SerializedName("commentnum")
                        val commentnum: Int = 0,
                        @SerializedName("charge_edt")
                        val chargeEdt: String = "",
                        @SerializedName("charge_email")
                        val chargeEmail: String = "",
                        @SerializedName("previous_id")
                        val previousId: String = "",
                        @SerializedName("praisenum")
                        val praisenum: Int = 0,
                        @SerializedName("question_makettime")
                        val questionMakettime: String = "",
                        @SerializedName("asker")
                        val asker: Asker,
                        @SerializedName("author_list")
                        val authorList: List<AuthorListItem>?,
                        @SerializedName("sharenum")
                        val sharenum: Int = 0,
                        @SerializedName("question_id")
                        val questionId: String = "",
                        @SerializedName("asker_list")
                        val askerList: List<AskerListItem>?,
                        @SerializedName("cover_media_type")
                        val coverMediaType: String = "",
                        @SerializedName("web_url")
                        val webUrl: String = "",
                        @SerializedName("cover_media_file")
                        val coverMediaFile: String = "",
                        @SerializedName("anchor")
                        val anchor: String = "",
                        @SerializedName("answer_title")
                        val answerTitle: String = "",
                        @SerializedName("question_content")
                        val questionContent: String = "",
                        @SerializedName("next_id")
                        val nextId: Int = 0)