package seph.android.com.itemtracker.viewmodel

import seph.android.com.itemtracker.data.Result
import seph.android.com.itemtracker.data.interactor.GetItems
import seph.android.com.itemtracker.model.Item
import seph.android.com.itemtracker.viewmodel.base.BaseViewModel

/**
 * Created by seph on 03/05/2018.
 */

class MainViewModel(var getItems : GetItems) : BaseViewModel<List<Item>>() {

    fun getItems() {
        getItems.execute().subscribe({
            when(it) {
                is Result.Success<*> -> liveData.value = it.data as List<Item>
                is Result.Error -> doOnError(it.message)
            }
        })
    }

    fun doOnError(message : String) {
        TODO()
    }
}