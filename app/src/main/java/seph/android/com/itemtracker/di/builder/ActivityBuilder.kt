package seph.android.com.itemtracker.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import seph.android.com.itemtracker.di.module.AddItemModule
import seph.android.com.itemtracker.di.module.MainModule
import seph.android.com.itemtracker.view.ui.AddItemActivity
import seph.android.com.itemtracker.view.ui.MainActivity

/**
 * Created by seph on 03/05/2018.
 */

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(MainModule::class))
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = arrayOf(AddItemModule::class))
    abstract fun bindAddItemActivity(): AddItemActivity
}