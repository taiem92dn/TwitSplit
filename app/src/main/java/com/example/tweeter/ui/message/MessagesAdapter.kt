package com.example.tweeter.ui.message

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tweeter.R
import kotlinx.android.synthetic.main.item_message.view.*

class MessagesAdapter(data : MutableList<String>) : RecyclerView.Adapter<MessagesAdapter.ViewHolder>() {

    var mData : MutableList<String> = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun addItem(item : String) {
        if (!TextUtils.isEmpty(item)) {
            mData.add(item)
            notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvMessage.text = mData.get(position)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}