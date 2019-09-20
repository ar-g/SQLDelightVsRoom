package e.ar_g.room.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import e.ar_g.room.R
import e.ar_g.room.data.MessageWithMedia

class JoinAdapter : ListAdapter<MessageWithMedia, MessageViewHolder>(object : DiffUtil.ItemCallback<MessageWithMedia>() {
    override fun areItemsTheSame(oldItem: MessageWithMedia, newItem: MessageWithMedia) = oldItem.messageId == newItem.messageId

    override fun areContentsTheSame(oldItem: MessageWithMedia, newItem: MessageWithMedia)
            = oldItem == newItem
}){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_message, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        getItem(position)?.let {
            holder.itemView.findViewById<TextView>(R.id.tvMessage).text = "Payload:${it.msg} \nMedia:${it.url}"
        }
    }

}