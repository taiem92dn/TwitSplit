package com.example.tweeter.ui.message

import com.example.tweeter.ui.base.IBaseView

interface IMessageView : IBaseView {

    fun addMessages(messages : List<String>)

    fun showError(message : String)
}