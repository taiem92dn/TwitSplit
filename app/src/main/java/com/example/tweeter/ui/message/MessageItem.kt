package com.example.tweeter.ui.message

import android.os.Parcel
import android.os.Parcelable

class MessageItem() : Parcelable{

    var text : String ?= null

    var color : Int ?= null

    constructor(parcel: Parcel) : this() {
        text = parcel.readString()
        color = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    constructor(text: String?, color: Int?) : this() {
        this.text = text
        this.color = color
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(text)
        parcel.writeValue(color)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MessageItem> {
        override fun createFromParcel(parcel: Parcel): MessageItem {
            return MessageItem(parcel)
        }

        override fun newArray(size: Int): Array<MessageItem?> {
            return arrayOfNulls(size)
        }
    }


}