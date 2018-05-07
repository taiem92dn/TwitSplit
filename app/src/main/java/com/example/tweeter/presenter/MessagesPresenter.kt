package com.example.tweeter.presenter

import com.example.tweeter.ui.message.IMessageView
import com.example.tweeter.util.SplitMessagesUtil
import javax.inject.Inject

class MessagesPresenter @Inject constructor() : IBasePresenter<IMessageView> {

    private var mMessagesView : IMessageView ?= null

    override fun setView(messagesView: IMessageView) {
        mMessagesView = messagesView
    }

    fun processMessages(message : String) {
        val result = SplitMessagesUtil.splitMessage(message)

        if (result?.size == 0) {
            mMessagesView?.showError("Error input message!")
        }
        else {
            mMessagesView?.addMessages(result!!)
        }
    }

    override fun destroyView() {
        mMessagesView = null
    }
}