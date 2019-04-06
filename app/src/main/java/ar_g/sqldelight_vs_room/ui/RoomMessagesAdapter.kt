package ar_g.sqldelight_vs_room.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import ar_g.sqldelight_vs_room.R
import ar_g.sqldelight_vs_room.room.Message

class RoomMessagesAdapter : PagedListAdapter<Message, MessageViewHolder>(object : DiffUtil.ItemCallback<Message>() {
    override fun areItemsTheSame(oldItem: Message, newItem: Message) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Message, newItem: Message)
            = oldItem == newItem
}){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_message, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        getItem(position)?.let {
            holder.itemView.findViewById<TextView>(R.id.tvMessage).text = "${it.payload} \n ${it.media_id}"
        }
    }

}