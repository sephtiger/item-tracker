package seph.android.com.itemtracker

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import seph.android.com.itemtracker.di.component.DaggerAppComponent
import javax.inject.Inject

/**
 * Created by seph on 03/05/2018.
 */

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override
    fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

    override
    fun activityInjector(): AndroidInjector<Activity> = activityInjector
}