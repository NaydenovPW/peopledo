package pw.naydenov.peopledo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pw.naydenov.peopledo.R
import pw.naydenov.peopledo.domain.Message

class MessagesAdapter(val items: List<Message>): RecyclerView.Adapter<MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.viewholder_message, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.setContent(items[position])
    }

}
