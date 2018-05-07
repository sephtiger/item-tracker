package seph.android.com.itemtracker.data.interactor

import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import seph.android.com.itemtracker.data.Result
import seph.android.com.itemtracker.data.source.firebase.FirebaseItemRepository
import seph.android.com.itemtracker.model.Item
import java.util.*

/**
 * Created by seph on 05/05/2018.
 */

class GetItemsTest {

    @Mock
    lateinit var repository: FirebaseItemRepository

    lateinit var getItems : GetItems

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getItems = GetItems(repository)
    }

    @Test
    fun get_items_success() {

        Mockito.`when`(repository.getItems()).thenReturn(Flowable.just(Arrays.asList(
            Item("Item 1", "Some Description", "http://some.url","Some Location", 1),
            Item("Item 2", "Some Description", "http://some.url","Some Location", 1),
            Item("Item 3", "Some Description", "http://some.url","Some Location", 1)
        )))
        var result = TestSubscriber.create<Result>()
        getItems.execute().blockingSubscribe(result)

        result.assertValue { it is Result.Success<*> && it.data is List<*> && (it.data as List<*>).size == 3}
        result.assertNoErrors()
        result.assertComplete()
    }

    @Test
    fun get_items_failure() {

        Mockito.`when`(repository.getItems()).thenReturn(Flowable.error(Throwable("Some error")))

        var result = getItems.execute().test()

        result.assertValue { it is Result.Error }
        result.assertNotComplete()
    }
}