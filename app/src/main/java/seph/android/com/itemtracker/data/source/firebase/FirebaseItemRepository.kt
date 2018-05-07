package seph.android.com.itemtracker.data.source.firebase

import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.QueryDocumentSnapshot
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import seph.android.com.itemtracker.data.ItemRepository
import seph.android.com.itemtracker.model.Item

/**
 * Created by seph on 03/05/2018.
 */

class FirebaseItemRepository(var firebaseDatabase: FirebaseDatabase) : ItemRepository {

    override
    fun addItem(name: String, description: String, image: String, location: String, cost: Int):
                Flowable<Boolean> = Flowable.create({

            var item = Item(name, description, image, location, cost)
            firebaseDatabase.addItem(item)
                .addOnSuccessListener { _ -> it.onNext(true) }
                .addOnFailureListener { _ -> it.onError(Throwable("Error saving item")) }
                .addOnCompleteListener { _ -> it.onComplete() }

        }, BackpressureStrategy.BUFFER)

    override fun editItem(id: String, name: String, description: String, image: String,
            location: String, cost: Int): Flowable<Boolean> = Flowable.create({

        var item = Item(name, description, image, location, cost)
        firebaseDatabase.editItem(id, item)
                .addOnSuccessListener { _ -> it.onNext(true) }
                .addOnFailureListener { _ -> it.onError(Throwable("Error saving item")) }
                .addOnCompleteListener { _ -> it.onComplete() }

    }, BackpressureStrategy.BUFFER)

    override
    fun deleteItem(id: String): Flowable<Boolean> = Flowable.create({

            firebaseDatabase.deleteItem(id)
                .addOnSuccessListener { _ -> it.onNext(true) }
                .addOnFailureListener { _ -> it.onError(Throwable("Error deleting item")) }
                .addOnCompleteListener { _ -> it.onComplete() }

        }, BackpressureStrategy.BUFFER)

    override
    fun getItems(): Flowable<List<Item>> = Flowable.create( {
        val list = ArrayList<Item>()
        firebaseDatabase.getItems()
            .addSnapshotListener { querySnapshots, _ ->
                run {
                    querySnapshots?.documentChanges?.forEach {
                        when (it.type) {
                            DocumentChange.Type.ADDED -> list.add(createItemFromDocument(it.document))
                            DocumentChange.Type.MODIFIED -> {
                                list.remove(createItemFromDocument(it.document))
                                list.add(createItemFromDocument(it.document))
                            }
                            DocumentChange.Type.REMOVED -> list.remove(createItemFromDocument(it.document))
                            else -> TODO()
                        }
                    }

                    it.onNext(list)
                }
            }
        }, BackpressureStrategy.BUFFER)

    private fun createItemFromDocument(document: QueryDocumentSnapshot) : Item {
        var item = document.toObject(Item::class.java)
        item.id = document.id
        return item
    }
}