package com.example.tweeter.ui.message

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

class MessagesAdapter : RecyclerView.Adapter<MessagesAdapter.ViewHolder>() {

    var mData : ArrayList<MessageItem> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun addItem(item : String) {
        if (!TextUtils.isEmpty(item)) {
            mData.add(MessageItem(item, Utils.randomColor()))
            notifyDataSetChanged()
        }
    }

    fun addItems(items : List<String>) {
        if (items.isNotEmpty()) {
            var color = Utils.randomColor()

            for (i in items.size-1 downTo  0) {
                mData.add(0, MessageItem(items[i], color))
            }

            notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvMessage.text = mData.get(position).text
        val colorFilter = PorterDuffColorFilter(mData.get(position).color!!, PorterDuff.Mode.MULTIPLY)
        holder.itemView.background.colorFilter = colorFilter
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}