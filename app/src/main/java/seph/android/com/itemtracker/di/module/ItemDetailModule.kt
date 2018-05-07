package seph.android.com.itemtracker.di.module

import dagger.Module
import dagger.Provides
import seph.android.com.itemtracker.viewmodel.ItemDetailViewModel

/**
 * Created by seph on 07/05/2018.
 */

@Module
class ItemDetailModule {

    @Provides
    fun provideViewModel() = ItemDetailViewModel()
}