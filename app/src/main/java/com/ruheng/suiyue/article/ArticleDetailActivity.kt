package com.ruheng.suiyue.article

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.view.View
import com.gyf.barlibrary.ImmersionBar
import com.ruheng.suiyue.R
import com.ruheng.suiyue.data.bean.ArticleDetailBean
import com.ruheng.suiyue.data.bean.QuestionContentBean
import com.ruheng.suiyue.data.bean.SerialContentBean
import kotlinx.android.synthetic.main.activity_article_detail.*

/**
 * Created by lvruheng on 2018/3/7.
 */
class ArticleDetailActivity : AppCompatActivity(), ArticleDetailContract.View {

    private lateinit var mPresenter: ArticleDetailPresenter
    private var item_id: String = ""
    private var category: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)
        ImmersionBar.with(this).transparentBar().barAlpha(0.3f).fitsSystemWindows(true).init()
        val extras = intent.extras
        item_id = extras.get("item_id") as String
        category = extras.get("category") as String
        when (category) {
            "1" -> tv_article_title.text = "短文"
            "2" -> tv_article_title.text = "连载"
            else -> tv_article_title.text = "问答"
        }
        iv_article_back.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        mPresenter = ArticleDetailPresenter(this)
        mPresenter.start(category, item_id)
    }

    override fun setPresenter(presenter: ArticleDetailContract.Presenter) {
        //省略
    }

    override fun getDetailContext(): Context? {
        return this
    }

    override fun setArticle(articleDetailBean: ArticleDetailBean) {
        val data = articleDetailBean.data
        val hpTitle = data.hpTitle
        val userName = data.author?.get(0)?.userName
        val hpContent = data.hpContent
        tv_content_title.text = hpTitle
        tv_content_author.text = "作者：" + userName
        tv_content.text = Html.fromHtml(hpContent)
    }

    override fun setSerial(serialContentBean: SerialContentBean) {
        val data = serialContentBean.data
        val title = data.title
        val userName = data.author.userName
        val content = data.content
        tv_content_title.text = title
        tv_content_author.text = "作者：" + userName
        tv_content.text = Html.fromHtml(content)

    }

    override fun setQuestion(questionContentBean: QuestionContentBean) {
        val data = questionContentBean.data
        val title = data.questionTitle
        val questionContent = data.questionContent
        val askerName = data.asker.userName
        val answerName = data.answerer.userName
        val answerContent = data.answerContent
        tv_content_title.text = title
        tv_content_author.text = askerName + "问："
        tv_question_content.visibility = View.VISIBLE
        tv_question_content.text = questionContent
        divider.visibility = View.VISIBLE
        tv_answer_author.text = answerName + "答："
        tv_content.text = Html.fromHtml(answerContent)


    }
}
