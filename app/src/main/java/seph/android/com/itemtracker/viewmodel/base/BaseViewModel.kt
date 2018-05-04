package seph.android.com.itemtracker.viewmodel.base;

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by seph on 04/05/2018.
 */
abstract class BaseViewModel<T> : ViewModel() {

    val liveData : MutableLiveData<T> = MutableLiveData()

    val state : MutableLiveData<State> = MutableLiveData()

    sealed class State {
        object Loading : State()
        object Success : State()
        class Error(val message : String) : State()
    }
}
