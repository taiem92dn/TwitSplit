package com.example.tweeter.ui.message

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tweeter.R
import com.example.tweeter.util.Utils
import kotlinx.android.synthetic.main.item_message.view.*
import kotlin.collections.ArrayList

class MessagesAdapter : RecyclerView.Adapter<MessagesAdapter.ViewHolder>() {

    var mData : MutableList<MessageItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun addItem(item : String) {
        if (!TextUtils.isEmpty(item)) {
            mData.add(createItem(item))
            notifyDataSetChanged()
        }
    }

    fun addItems(items : List<String>) {
        if (items.isNotEmpty()) {
            for (item in items) {
                mData.add(createItem(item))
            }

            notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvMessage.text = mData.get(position).text
        val colorFilter = PorterDuffColorFilter(mData.get(position).color!!, PorterDuff.Mode.MULTIPLY)
        holder.itemView.background.colorFilter = colorFilter
    }

    private fun createItem(s : String, color: Color) : MessageItem {
        val item = MessageItem(s, Utils.randomColor())

        return item
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}