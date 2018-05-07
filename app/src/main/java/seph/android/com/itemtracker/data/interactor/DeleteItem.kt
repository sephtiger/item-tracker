package seph.android.com.itemtracker.data.interactor

import io.reactivex.Flowable
import seph.android.com.itemtracker.data.ItemRepository

/**
 * Created by seph on 03/05/2018.
 */

class DeleteItem(var repository: ItemRepository) : UseCase<Boolean>() {

    var id : String = ""

    override
    fun useCaseObservable() : Flowable<Boolean> = repository.deleteItem(id)
}