package seph.android.com.itemtracker.data.source.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import seph.android.com.itemtracker.model.Item
import javax.inject.Singleton

/**
 * Created by seph on 04/05/2018.
 */

class FirebaseDatabase(var firebaseFirestore: FirebaseFirestore) {

    companion object Tables {
        val ITEMS = "items"
    }

    fun getItems() : CollectionReference = firebaseFirestore.collection(Tables.ITEMS)

    fun addItem(item : Item) : Task<DocumentReference> =
        firebaseFirestore.collection(Tables.ITEMS).add(item)

}