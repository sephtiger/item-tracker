package seph.android.com.itemtracker.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import seph.android.com.itemtracker.App
import seph.android.com.itemtracker.di.builder.ActivityBuilder
import seph.android.com.itemtracker.di.module.AppModule
import javax.inject.Singleton

/**
 * Created by seph on 03/05/2018.
 */

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class,
        AppModule::class, ActivityBuilder::class))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app : Application) : Builder
        fun build(): AppComponent
    }

    fun inject(app: App)
}