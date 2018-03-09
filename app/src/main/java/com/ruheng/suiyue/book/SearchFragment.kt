package com.ruheng.suiyue.book

import android.app.DialogFragment
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import com.ruheng.suiyue.R
import com.ruheng.suiyue.util.KeyBoardUtils
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
        }
    }
}