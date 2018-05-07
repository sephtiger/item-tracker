package seph.android.com.itemtracker.di.module

import dagger.Module
import dagger.Provides
import seph.android.com.itemtracker.data.interactor.AddItem
import seph.android.com.itemtracker.data.interactor.DeleteItem
import seph.android.com.itemtracker.data.source.firebase.FirebaseItemRepository
import seph.android.com.itemtracker.viewmodel.ItemAddViewModel
import seph.android.com.itemtracker.viewmodel.ItemDetailViewModel

/**
 * Created by seph on 07/05/2018.
 */

@Module
class ItemDetailModule {

    @Provides
    fun provideDeleteItem(firebaseItemRepository: FirebaseItemRepository) = DeleteItem(firebaseItemRepository)

    @Provides
    fun provideViewModel(deleteItem : DeleteItem) = ItemDetailViewModel(deleteItem)
}