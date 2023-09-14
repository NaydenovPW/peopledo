package pw.naydenov.peopledo.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import javax.inject.Inject

class MessagesGenerator @Inject constructor() : SourceOfMessages {

    override fun messagesFlow(): Flow<Message> {
        var count = 0
        return merge(
            messagesEveryOneSecond(),
            messagesEveryTwoSeconds()
        ).map { message ->
            count++
            message.messageText = message.messageText + " [$count]"
            message
        }
    }

    private fun messagesEveryOneSecond(): Flow<Message> =
        flow<Message> {
            while (true) {
                delay(1000)
                emit(Message("Message source one", false))
            }
        }.flowOn(Dispatchers.Default)

    private fun messagesEveryTwoSeconds(): Flow<Message> =
        flow<Message> {
            while (true) {
                delay(2000)
                emit(Message("Message source two", true))
            }
        }.flowOn(Dispatchers.Default)

}
