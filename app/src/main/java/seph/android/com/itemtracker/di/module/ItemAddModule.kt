package seph.android.com.itemtracker.di.module

import dagger.Module
import dagger.Provides
import seph.android.com.itemtracker.data.interactor.AddItem
import seph.android.com.itemtracker.data.interactor.EditItem
import seph.android.com.itemtracker.data.source.firebase.FirebaseItemRepository
import seph.android.com.itemtracker.viewmodel.ItemAddViewModel

/**
 * Created by seph on 03/05/2018.
 */

@Module
class ItemAddModule {

    @Provides
    fun provideAddItem(firebaseItemRepository: FirebaseItemRepository) = AddItem(firebaseItemRepository)

    @Provides
    fun provideEditItem(firebaseItemRepository: FirebaseItemRepository) = EditItem(firebaseItemRepository)

    @Provides
    fun provideViewModel(addItem : AddItem, editItem: EditItem) = ItemAddViewModel(addItem, editItem)

}