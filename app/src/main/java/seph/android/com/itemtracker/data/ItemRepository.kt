package seph.android.com.itemtracker.data

import io.reactivex.Flowable
import seph.android.com.itemtracker.model.Item

/**
 * Created by seph on 03/05/2018.
 */

interface ItemRepository {

    fun addItem(name: String, description: String, image: String, location: String, cost: Int) : Flowable<Boolean>

    fun editItem(id: String, name : String, description: String, image : String, location: String, cost : Int) : Flowable<Boolean>

    fun deleteItem(id: String) : Flowable<Boolean>

    fun getItems() : Flowable<List<Item>>

}