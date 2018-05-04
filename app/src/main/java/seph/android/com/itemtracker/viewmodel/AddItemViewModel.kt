package seph.android.com.itemtracker.viewmodel

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import seph.android.com.itemtracker.data.Result
import seph.android.com.itemtracker.data.interactor.AddItem
import seph.android.com.itemtracker.model.Item
import seph.android.com.itemtracker.viewmodel.base.BaseViewModel

/**
 * Created by seph on 03/05/2018.
 */

class AddItemViewModel(var addItem : AddItem) : BaseViewModel<Item>() {

    fun addItem(name: String, description: String, image: Uri, location: String, cost: Int) {

        state.value = State.Loading

        var imageRef = FirebaseStorage.getInstance().getReference("images/" + image.lastPathSegment)

        imageRef.putFile(image).addOnSuccessListener {

            with(addItem) {
                this.name = name
                this.description = description
                this.image = it.downloadUrl.toString()
                this.location = location
                this.cost = cost
            }

            addItem.execute().subscribe({
                when(it) {
                    is Result.Success<*> -> state.value = State.Success
                    is Result.Error -> doOnError(it.message)
                }

            })
        }.addOnFailureListener { doOnError(it.toString()) }
    }

    fun doOnError(message : String) {
        state.value = State.Error(message)
    }
}