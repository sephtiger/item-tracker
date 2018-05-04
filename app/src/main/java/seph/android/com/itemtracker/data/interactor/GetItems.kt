package seph.android.com.itemtracker.data.interactor

import io.reactivex.Flowable
import seph.android.com.itemtracker.data.ItemRepository
import seph.android.com.itemtracker.model.Item

/**
 * Created by seph on 03/05/2018.
 */

class GetItems(var repository: ItemRepository) : UseCase<List<Item>>() {

    override
    fun useCaseObservable() : Flowable<List<Item>> = repository.getItems()
}