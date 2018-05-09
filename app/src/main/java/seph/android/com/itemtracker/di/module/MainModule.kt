package seph.android.com.itemtracker.di.module;

import dagger.Module
import dagger.Provides
import seph.android.com.itemtracker.data.interactor.GetItems
import seph.android.com.itemtracker.data.source.firebase.FirebaseItemRepository
import seph.android.com.itemtracker.viewmodel.MainViewModel

/**
 * Created by seph on 03/05/2018.
 */

@Module
class MainModule {

    @Provides
    fun provideGetItems(firebaseItemRepository : FirebaseItemRepository) = GetItems(firebaseItemRepository)

    @Provides
    fun provideViewModel(getItems : GetItems) = MainViewModel(getItems)
}
