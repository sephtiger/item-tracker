package seph.android.com.itemtracker.data.source.firebase

import com.google.firebase.firestore.DocumentChange
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

    override fun editItem(name: String, description: String, image: String, location: String, cost: Int): Flowable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteItem(): Flowable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override
    fun getItems(): Flowable<List<Item>> = Flowable.create( {
        val list = ArrayList<Item>()
        firebaseDatabase.getItems()
            .addSnapshotListener { querySnapshots, _ ->
                run {
                    querySnapshots?.documentChanges?.forEach {
                        when (it.type) {
                            DocumentChange.Type.ADDED -> list.add(it.document.toObject(Item::class.java))
                            DocumentChange.Type.MODIFIED -> list.add(it.document.toObject(Item::class.java))
                            DocumentChange.Type.REMOVED -> list.remove(it.document.toObject(Item::class.java))
                            else -> TODO()
                        }
                    }

                    it.onNext(list)
                }
            }
        }, BackpressureStrategy.BUFFER)

}