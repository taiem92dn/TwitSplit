package com.example.tweeter.presenter

import com.example.tweeter.ui.message.IMessageView
import javax.inject.Inject

class MessagesPresenter @Inject constructor() : IBasePresenter<IMessageView> {

    private var mMessagesView : IMessageView ?= null

    override fun setView(messagesView: IMessageView) {
        mMessagesView = messagesView
    }

    override fun destroyView() {
        mMessagesView = null
    }
}