package seph.android.com.itemtracker.viewmodel

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import seph.android.com.itemtracker.data.Result
import seph.android.com.itemtracker.data.interactor.AddItem
import seph.android.com.itemtracker.data.interactor.EditItem
import seph.android.com.itemtracker.model.Item
import seph.android.com.itemtracker.viewmodel.base.BaseViewModel

/**
 * Created by seph on 03/05/2018.
 */

class ItemAddViewModel(var addItem : AddItem, var editItem: EditItem) : BaseViewModel<Item>() {

    fun uploadImage(image: Uri) : Flowable<String> = Flowable.create({ emitter ->

            var imageRef = FirebaseStorage.getInstance().getReference("images/" + image.lastPathSegment)

            imageRef.putFile(image).addOnSuccessListener {

                emitter.onNext(it.downloadUrl.toString())
                emitter.onComplete()

            }.addOnFailureListener { doOnError(it.toString()) }

        }, BackpressureStrategy.BUFFER)

    fun addItem(name: String, description: String, image: Uri, location: String, cost: Int) {

        state.value = State.Loading

        uploadImage(image)
            .subscribe({

                with(addItem) {
                    this.name = name
                    this.description = description
                    this.image = it
                    this.location = location
                    this.cost = cost
                }

                addItem.execute().subscribe({
                    when(it) {
                        is Result.Success<*> -> state.value = State.Success
                        is Result.Error -> doOnError(it.message)
                    }

                })
            })
    }

    fun editItem(id: String, name: String, description: String, image: String, location: String, cost: Int) {

        state.value = State.Loading

        with(editItem) {
            this.id = id
            this.name = name
            this.description = description
            this.image = image
            this.location = location
            this.cost = cost
        }

        editItem.execute().subscribe({
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