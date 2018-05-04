package seph.android.com.itemtracker.data.interactor

import io.reactivex.Flowable
import seph.android.com.itemtracker.data.ItemRepository

/**
 * Created by seph on 03/05/2018.
 */

class AddItem(var repository: ItemRepository) : UseCase<Boolean>() {

    var name : String = ""
    var description : String = ""
    var image : String = ""
    var location : String = ""
    var cost : Int = 0

    override
    fun useCaseObservable() : Flowable<Boolean> =
            repository.addItem(name, description, image, location, cost)
}