package seph.android.com.itemtracker.data.interactor

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import seph.android.com.itemtracker.data.Result

/**
 * Created by seph on 04/05/2018.
 */

abstract class UseCase<T> {

    abstract fun useCaseObservable() : Flowable<T>

    fun execute() : Flowable<Result> {

        return useCaseObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError({
                    Result.Error(it.toString())
                })
                .map { Result.Success(it) }
    }
}