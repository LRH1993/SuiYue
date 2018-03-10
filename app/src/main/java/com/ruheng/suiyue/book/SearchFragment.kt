package com.ruheng.suiyue.book

import android.app.DialogFragment
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import com.ruheng.suiyue.R
import com.ruheng.suiyue.util.KeyBoardUtils
import com.ruheng.suiyue.util.SPUtils
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * Created by lvruheng on 2018/3/9.
 */
const val SEARCH_TAG = "SearchFragment"

class SearchFragment : DialogFragment(), CircularRevealAnim.AnimListener,
        ViewTreeObserver.OnPreDrawListener, DialogInterface.OnKeyListener,
        View.OnClickListener {
    private lateinit var mRootView: View
    private lateinit var mCircularRevealAnim: CircularRevealAnim
    private lateinit var mHotAdapter: HotAdapter
    private lateinit var mHistoryAdapter: HistoryAdapter
    var mHistorySet = HashSet<String>()
    var mHistoryList = ArrayList<String>()
    var mHotList = listOf<String>("解忧杂货店", "三体", "万历十五年", "活着", "摆渡人", "我们仨", "天才在左 疯子在右", "恋情的终结", "房思琪的初恋乐园")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogStyle)

    }

    override fun onStart() {
        super.onStart()
        initDialog()
    }

    private fun initDialog() {
        val window = dialog.window
        window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        window.setGravity(Gravity.TOP)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mRootView = inflater?.inflate(R.layout.fragment_search, container, false)!!

        return mRootView
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }


    private fun init() {
        mCircularRevealAnim = CircularRevealAnim()
        mCircularRevealAnim.setAnimListener(this)
        dialog.setOnKeyListener(this)
        iv_search.viewTreeObserver.addOnPreDrawListener(this)
        iv_search.setOnClickListener(this)
        iv_search_back.setOnClickListener(this)
        rv_hot.layoutManager = LinearLayoutManager(activity)
        mHotAdapter = HotAdapter(activity, mHotList)
        rv_hot.adapter = mHotAdapter
        setHistory()

    }

    /**
     * 初始化历史搜索记录
     */
    private fun setHistory() {
        val historySet = SPUtils.getInstance(activity, "book").getStringSet("history", mHistorySet)
        if (historySet.isEmpty()) {
            rl_history.visibility = View.GONE
            rv_history.visibility = View.GONE
        } else {
            rl_history.visibility = View.VISIBLE
            rv_history.visibility = View.VISIBLE
            mHistoryList.clear()
            historySet.forEach {
                mHistoryList.add(it)
            }
            rv_history.layoutManager = GridLayoutManager(activity, 2)
            mHistoryAdapter = HistoryAdapter(activity, mHistoryList)
            rv_history.adapter = mHistoryAdapter
            tv_history_clear.setOnClickListener(this)
        }
    }


    override fun onKey(dialog: DialogInterface?, keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_UP) {
            hideAnim()
        } else if (keyCode == KeyEvent.KEYCODE_ENTER && event?.action == KeyEvent.ACTION_DOWN) {
            search()
        }
        return false
    }

    private fun search() {
        val searchKey = et_search_keyword.text.toString()
        if (TextUtils.isEmpty(searchKey.trim({ it <= ' ' }))) {
            Toast.makeText(activity, "请输入关键字", Toast.LENGTH_SHORT).show()
        } else {
            var set = setOf<String>(et_search_keyword.text.toString())
            SPUtils.getInstance(activity, "book").put("history", set)
            //跳转到搜索结果页面
            var keyWord = et_search_keyword.text.toString()
            var intent = Intent(activity, SearchResultActivity::class.java)
            intent.putExtra("keyWord", keyWord)
            activity?.startActivity(intent)
        }
    }

    private fun hideAnim() {
        KeyBoardUtils.closeKeyboard(activity, et_search_keyword);
        mCircularRevealAnim.hide(iv_search, mRootView)
    }

    override fun onHideAnimationEnd() {
        et_search_keyword.setText("")
        dismiss()
    }

    override fun onShowAnimationEnd() {
        if (isVisible) {
            KeyBoardUtils.openKeyboard(activity, et_search_keyword)
        }
    }

    override fun onPreDraw(): Boolean {
        iv_search.viewTreeObserver.removeOnPreDrawListener(this)
        mCircularRevealAnim.show(iv_search, mRootView)
        return true
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_search_back -> {
                hideAnim()
            }
            R.id.iv_search -> {
                search()
            }
            R.id.tv_history_clear -> {
                SPUtils.getInstance(activity, "book").remove("history")
                mHistoryList.clear()
                rv_history.adapter.notifyDataSetChanged()
                rl_history.visibility = View.GONE
                rv_history.visibility = View.GONE
            }
        }
    }
}