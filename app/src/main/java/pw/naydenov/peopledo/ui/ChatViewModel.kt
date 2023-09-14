package pw.naydenov.peopledo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pw.naydenov.peopledo.domain.Message
import pw.naydenov.peopledo.domain.SourceOfMessages
import java.util.LinkedList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatViewModel @Inject constructor(
    private val sourceOfMessages: SourceOfMessages
): ViewModel() {

    private val messagesList = LinkedList<Message>()
    private val messagesAdapter = MessagesAdapter(messagesList)

    private val _messagesAdapterSource = MutableLiveData<MessagesAdapter>(messagesAdapter)
    val messagesAdapterSource: LiveData<MessagesAdapter> = _messagesAdapterSource

    private val _scrollPositionSource = MutableLiveData<Int>(0)
    val scrollPositionSource: LiveData<Int> = _scrollPositionSource

    init {
//        messagesList.add(Message("Message One"))
//        messagesList.add(Message("Message Two"))
//        messagesList.add(Message("Message Three"))
//        messagesList.add(Message("Message Four"))
//        messagesList.add(Message("Message Five"))
//        messagesList.add(Message("Message Six"))
//        messagesList.add(Message("Message Seven"))
//        messagesList.add(Message("Message Eight"))
//        messagesList.add(Message("Message Nine"))
//        messagesList.add(Message("Message ZERO"))
//        _messagesAdapterSource.value = messagesAdapter

        listenForMessages()

    }

    private fun listenForMessages() {
        viewModelScope.launch {
            sourceOfMessages.messagesFlow().collect { message ->
                addMessage(message)
            }
        }
    }

    private fun addMessage(message: Message) {
        messagesList.add(message)
        messagesAdapter.notifyItemInserted(messagesList.size - 1)
        _scrollPositionSource.value = messagesList.size - 1
    }

    fun onMessageButtonPressed(text: String) {
        addMessage(Message(text))
    }

}
