package seph.android.com.itemtracker.data.interactor

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import seph.android.com.itemtracker.data.Result

/**
 * Created by seph on 04/05/2018.
 */

abstract class UseCase<T> {

    abstract fun useCaseObservable() : Flowable<T>

    fun execute() : Flowable<Result> {

        return Flowable.create({ emitter -> useCaseObservable()
                .subscribe(
                { emitter.onNext(Result.Success(it)) },
                { emitter.onNext(Result.Error(it.message!!)) },
                { emitter.onComplete() }
            )

        }, BackpressureStrategy.BUFFER)
    }
}