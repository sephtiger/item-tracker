package seph.android.com.itemtracker.di.module

import dagger.Module
import dagger.Provides
import seph.android.com.itemtracker.data.interactor.AddItem
import seph.android.com.itemtracker.data.source.firebase.FirebaseItemRepository
import seph.android.com.itemtracker.viewmodel.AddItemViewModel

/**
 * Created by seph on 03/05/2018.
 */

@Module
class AddItemModule {

    @Provides
    fun provideAddItem(firebaseItemRepository: FirebaseItemRepository) = AddItem(firebaseItemRepository)

    @Provides
    fun provideViewModel(addItem : AddItem) = AddItemViewModel(addItem)

}