package com.example.tweeter.ui.message

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tweeter.R
import com.example.tweeter.di.component.AppComponent
import com.example.tweeter.presenter.MessagesPresenter
import com.example.tweeter.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_messages.*
import javax.inject.Inject

class MessagesFragment : BaseFragment(), IMessageView{

    private val TAG = MessagesFragment::class.simpleName

    @Inject
    lateinit var mMessagesPresenter : MessagesPresenter

    companion object {

        @JvmStatic
        fun newInstance(): MessagesFragment? = MessagesFragment()

    }

    var mAdapter : MessagesAdapter ?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messages, container, false)
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

        mAdapter = MessagesAdapter(mutableListOf("message 1", "message 2", "message 3"))

        rvMessages.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            this.adapter = mAdapter
        }

        btPost.setOnClickListener {
            mAdapter?.addItem(etInputMessage.text.toString())
            etInputMessage.text = null
        }


    }


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun getContext(): Context = activity as Context
}
