package pw.naydenov.peopledo.di

import dagger.Component
import pw.naydenov.peopledo.ui.ChatActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class])
interface AppComponent {

    fun inject(activity: ChatActivity)

}
