package com.example.tweeter.ui.message

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.*
import android.widget.Toast
import com.example.tweeter.R
import com.example.tweeter.di.component.AppComponent
import com.example.tweeter.presenter.MessagesPresenter
import com.example.tweeter.ui.base.BaseFragment
import kotlinx.android.synthetic.main.dialog_input.view.*
import kotlinx.android.synthetic.main.fragment_messages.*
import javax.inject.Inject

class MessagesFragment : BaseFragment(), IMessageView{


    @Inject
    lateinit var mMessagesPresenter : MessagesPresenter

    companion object {

        @JvmStatic
        fun newInstance(): MessagesFragment? = MessagesFragment()

        private val TAG = MessagesFragment::class.simpleName
        private const val KEY_MESSAGE_DATA = "KEY_MESSAGE_DATA"
    }

    private lateinit var mAdapter : MessagesAdapter

    private  var mDialog : Dialog ?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        mAdapter = MessagesAdapter()
        savedInstanceState ?.let{
            mAdapter.mData = it.getParcelableArrayList(KEY_MESSAGE_DATA)
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messages, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelableArrayList(KEY_MESSAGE_DATA, mAdapter.mData)
    }


    override fun onScreenVisible() {
        super.onScreenVisible()

        initialize()

        setupUI()
    }

    private fun initialize() {
        getComponent(AppComponent::class.java).inject(this)
        mMessagesPresenter.setView(this)
    }

    private fun setupUI() {
        rvMessages.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            this.adapter = mAdapter
        }

        fab.setOnClickListener({
            showDialog()
        })

    }

    private fun showDialog() {
        if (mDialog == null) {
            mDialog = createDialog()
        }

        mDialog?.show()
    }

    private fun createDialog() : Dialog {

        val dialogView = View.inflate(activity, R.layout.dialog_input, null)

        val dialog = Dialog(activity, R.style.DialogStyle)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(dialogView)

        dialogView.ivClose.setOnClickListener {
            revealShow(dialogView, false, dialog)
        }

        dialog.setOnShowListener {
            revealShow(dialogView, true, null)
        }

        dialog.setOnKeyListener { dialogInterface, i, keyEvent ->

            if (i == KeyEvent.KEYCODE_BACK) {
                revealShow(dialogView, false, dialog)
                true
            }
            false

        }

        dialog.window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        // handle post button
        dialogView.btPost.setOnClickListener {
            mMessagesPresenter.processMessages(dialogView.etInputMessage.text.toString())
            dialogView.etInputMessage.text = null
            revealShow(dialogView, false, dialog)
        }

        dialogView.etInputMessage.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, p1: Int, p2: Int, p3: Int) {
                dialogView.btPost.isEnabled = !TextUtils.isEmpty(charSequence)
            }

        })

        return dialog
    }

    private fun revealShow(dialogView : View, b : Boolean, dialog: Dialog?) {
        val view = dialogView.dialog

        val w = view.width
        val h = view.height

        val endRadius = Math.hypot(w.toDouble(), h.toDouble())

        val cx : Int = (fab.x + fab.width/2).toInt()
        val cy : Int = (fab.y + fab.height + 56).toInt()

        if (b) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val revealAnimator = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, endRadius.toFloat())

                view.visibility = View.VISIBLE
                revealAnimator.duration = 400
                revealAnimator.start()
            }
            else {
                view.visibility = View.VISIBLE
            }
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val animator = ViewAnimationUtils.createCircularReveal(view, cx, cy, endRadius.toFloat(), 0f)
                animator.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        dialog?.dismiss()
                        view.visibility = View.INVISIBLE
                    }
                })

                animator.duration = 200
                animator.start()
            }
            else {
                dialog?.dismiss()
                view.visibility = View.INVISIBLE
            }
        }
    }

    override fun addMessages(messages: List<String>) {
        mAdapter.addItems(messages)
    }

    override fun showError(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun getContext(): Context = activity as Context
}
