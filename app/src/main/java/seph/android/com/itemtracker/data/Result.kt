package seph.android.com.itemtracker.data

/**
 * Created by seph on 03/05/2018.
 */

sealed class Result {

    class Success<T>(val data : T) : Result()

    class Error(val message : String) : Result()
}