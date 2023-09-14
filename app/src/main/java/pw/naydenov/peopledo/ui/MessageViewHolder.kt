package pw.naydenov.peopledo.ui

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import pw.naydenov.peopledo.R
import pw.naydenov.peopledo.domain.Message

class MessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private lateinit var messageText: AppCompatTextView

    fun setContent(message: Message) {
        itemView.findViewById<AppCompatTextView>(R.id.message_text).text = message.messageText
    }

}
