package seph.android.com.itemtracker.data.source.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import seph.android.com.itemtracker.model.Item
/**
 * Created by seph on 04/05/2018.
 */

class FirebaseDatabase(var firebaseFirestore: FirebaseFirestore) {

    companion object Tables {
        val ITEMS = "items"
    }

    fun getItems() : CollectionReference = firebaseFirestore.collection(Tables.ITEMS)

    fun addItem(item : Item) : Task<Void> {
        var id = firebaseFirestore.collection(Tables.ITEMS).document().id
        item.id = id
        return firebaseFirestore.collection(Tables.ITEMS).document(id).set(item, SetOptions.merge())
    }


    fun editItem(id: String, item : Item) : Task<Void> =
        firebaseFirestore.collection(Tables.ITEMS).document(id).set(item, SetOptions.merge())

    fun deleteItem(id : String) : Task<Void> =
        firebaseFirestore.collection(Tables.ITEMS).document(id).delete()
}

