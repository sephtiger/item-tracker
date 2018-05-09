package seph.android.com.itemtracker.data.source.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import seph.android.com.itemtracker.model.Item

/**
 * Created by seph on 04/05/2018.
 */

class FirebaseDatabase(private var firebaseFirestore: FirebaseFirestore) {

    companion object Tables {
        const val ITEMS = "items"
    }

    fun getItems() : CollectionReference = firebaseFirestore.collection(Tables.ITEMS)

    fun addItem(item : Item) : Task<Void> {
        item.id = firebaseFirestore.collection(Tables.ITEMS).document().id
        return firebaseFirestore.collection(Tables.ITEMS).document(item.id).set(item)
    }


    fun editItem(id: String, item : Item) : Task<Void> =
        firebaseFirestore.collection(Tables.ITEMS).document(id).set(item, SetOptions.merge())

    fun deleteItem(id : String) : Task<Void> =
        firebaseFirestore.collection(Tables.ITEMS).document(id).delete()
}

