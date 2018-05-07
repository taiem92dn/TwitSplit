package com.example.tweeter.ui.message

class MessageItem {

    var text : String ?= null

    var color : Int ?= null

    constructor(text: String?, color: Int?) {
        this.text = text
        this.color = color
    }
}