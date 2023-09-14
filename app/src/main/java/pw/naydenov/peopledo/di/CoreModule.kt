package pw.naydenov.peopledo.di

import dagger.Module
import dagger.Provides
import pw.naydenov.peopledo.domain.MessagesGenerator
import pw.naydenov.peopledo.domain.SourceOfMessages
import javax.inject.Singleton

@Module
class CoreModule {

    @Provides
    @Singleton
    fun provideMessagesSource(): SourceOfMessages = MessagesGenerator()

}
