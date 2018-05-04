package seph.android.com.itemtracker.data.source.firebase

import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import seph.android.com.itemtracker.model.Item



/**
 * Created by seph on 04/05/2018.
 */

class FirebaseItemRepositoryTest {

    @Mock
    lateinit var firebaseDatabase : FirebaseDatabase

    lateinit var testItem : Item

    lateinit var firebaseItemRepository: FirebaseItemRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        firebaseItemRepository = FirebaseItemRepository(firebaseDatabase)
        testItem = Item("My Name", "Some Description", "http://some.url","Some Location", 1)
    }

    @Test
    fun addItem() {

        with(testItem) {

            var testSubscriber = firebaseItemRepository.addItem(name, description, image, location, cost)
                    .subscribeOn(Schedulers.single())
                    .test()

            testSubscriber
                    .assertNoErrors()
        }

    }

    @Test
    fun getItems() {
        firebaseItemRepository.getItems()
            .subscribeOn(Schedulers.single())
            .test()
            .assertNoErrors()
    }
}