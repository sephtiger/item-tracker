package seph.android.com.itemtracker.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import seph.android.com.itemtracker.di.module.ItemAddModule
import seph.android.com.itemtracker.di.module.ItemDetailModule
import seph.android.com.itemtracker.di.module.MainModule
import seph.android.com.itemtracker.view.ui.ItemAddActivity
import seph.android.com.itemtracker.view.ui.ItemDetailActivity
import seph.android.com.itemtracker.view.ui.MainActivity

/**
 * Created by seph on 03/05/2018.
 */

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(MainModule::class))
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = arrayOf(ItemDetailModule::class))
    abstract fun bindItemDetailActivity(): ItemDetailActivity

    @ContributesAndroidInjector(modules = arrayOf(ItemAddModule::class))
    abstract fun bindItemAddActivity(): ItemAddActivity
}