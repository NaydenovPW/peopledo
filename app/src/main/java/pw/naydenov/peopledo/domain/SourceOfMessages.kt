package pw.naydenov.peopledo.domain

import kotlinx.coroutines.flow.Flow

interface SourceOfMessages {
    fun messagesFlow(): Flow<Message>
}
