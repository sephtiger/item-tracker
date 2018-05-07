package seph.android.com.itemtracker.data.source.firebase

import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import seph.android.com.itemtracker.model.Item

/**
 * Created by seph on 04/05/2018.
 */

@RunWith(MockitoJUnitRunner::class)
class FirebaseItemRepositoryTest {

    @Mock
    lateinit var firebaseDatabase : FirebaseDatabase

    private var testItem = Item("My Name", "Some Description", "http://some.url","Some Location", 1)

    lateinit var firebaseItemRepository: FirebaseItemRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        firebaseItemRepository = FirebaseItemRepository(firebaseDatabase)
    }

    @Test
    fun addItem() {

        var result = firebaseItemRepository.addItem(
            testItem.name, testItem.description, testItem.image, testItem.location, testItem.cost)
            .subscribeOn(Schedulers.single())
            .test()

        result.assertNoErrors()

    }

    @Test
    fun getItems() {
        firebaseItemRepository.getItems()
            .subscribeOn(Schedulers.single())
            .test()
            .assertNoErrors()
    }
}