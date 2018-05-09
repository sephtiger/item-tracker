package seph.android.com.itemtracker.data.interactor

import io.reactivex.Flowable
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import seph.android.com.itemtracker.data.Result
import seph.android.com.itemtracker.data.source.firebase.FirebaseItemRepository
import seph.android.com.itemtracker.utils.RxImmediateSchedulerRule

/**
 * Created by seph on 07/05/2018.
 */

class DeleteItemTest {

    @Rule @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule
    @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var repository: FirebaseItemRepository

    lateinit var deleteItem: DeleteItem

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        deleteItem = DeleteItem(repository)
        deleteItem.id = "some-id"
    }

    @Test
    fun delete_item_success() {

        Mockito.`when`(repository.deleteItem("some-id"))
                .thenReturn(Flowable.just(true))

        var result = deleteItem.execute().test()

        result.assertValue { it is Result.Success<*> && it.data is Boolean && (it.data as Boolean)}
        result.assertNoErrors()
        result.assertComplete()
    }

    @Test
    fun delete_item_failure() {

        Mockito.`when`(repository.deleteItem("some-id"))
                .thenReturn(Flowable.error(Throwable("Error saving item")))

        var result = deleteItem.execute().test()

        result.assertValue { it is Result.Error }
        result.assertNotComplete()
    }
}