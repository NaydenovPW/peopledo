package pw.naydenov.peopledo

import android.app.Application
import pw.naydenov.peopledo.di.AppComponent
import pw.naydenov.peopledo.di.DaggerAppComponent

class ChatApplication: Application() {

    override fun onCreate() {
        super.onCreate()

         component = DaggerAppComponent.builder().build()

    }

    companion object {
        lateinit var component: AppComponent
        fun getAppComponent() = component
    }

}