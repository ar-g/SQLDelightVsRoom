package e.ar_g.delight.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import e.ar_g.delight.R
import schema.Messages

class Adapter : ListAdapter<Messages, MessageViewHolder>(object : DiffUtil.ItemCallback<Messages>() {
    override fun areItemsTheSame(oldItem: Messages, newItem: Messages) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Messages, newItem: Messages)
            = oldItem == newItem
}){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_message, parent, false)
        return MessageViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        getItem(position)?.let {
            holder.itemView.findViewById<TextView>(R.id.tvMessage).text = "Payload:${it.payload} \nMediaId:${it.media_id}"
        }
    }
}

class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)