package seph.android.com.itemtracker.viewmodel

import seph.android.com.itemtracker.data.Result
import seph.android.com.itemtracker.data.interactor.DeleteItem
import seph.android.com.itemtracker.model.Item
import seph.android.com.itemtracker.viewmodel.base.BaseViewModel

/**
 * Created by seph on 07/05/2018.
 */

class ItemDetailViewModel(var deleteItem : DeleteItem) : BaseViewModel<Item>() {

    fun delete() {

        state.value = State.Loading

        deleteItem.id = liveData.value!!.id

        deleteItem.execute().subscribe({
            when(it) {
                is Result.Success<*> -> state.value = State.Success
                is Result.Error -> doOnError(it.message)
            }

        })
    }

    fun doOnError(message : String) {
        state.value = State.Error(message)
    }
}