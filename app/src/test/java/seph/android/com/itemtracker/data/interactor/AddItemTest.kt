package seph.android.com.itemtracker.data.interactor

import io.reactivex.Flowable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import seph.android.com.itemtracker.data.Result
import seph.android.com.itemtracker.data.source.firebase.FirebaseItemRepository

/**
 * Created by sephremotigue on 05/05/2018.
 */

class AddItemTest {

    @Mock
    lateinit var repository: FirebaseItemRepository

    lateinit var addItem: AddItem

    private lateinit var scheduler : TestScheduler

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        scheduler = TestScheduler()
        addItem = AddItem(repository)
        addItem.name = "valid name"
        addItem.description = "some valid description"
        addItem.image = "http://vali/image.url"
        addItem.location = "valid location"
        addItem.cost = 999
    }

    @Test
    fun add_item_success() {

        Mockito.`when`(repository.addItem(
            addItem.name, addItem.description, addItem.image, addItem.location, addItem.cost))
            .thenReturn(Flowable.just(true))

        var result = addItem.execute().test()

        result.assertValue { it is Result.Success<*> && it.data is Boolean && (it.data as Boolean)}
        result.assertNoErrors()
        result.assertComplete()

    }

    @Test
    fun add_item_failure() {

        Mockito.`when`(repository.addItem(
                addItem.name, addItem.description, addItem.image, addItem.location, addItem.cost))
                .thenReturn(Flowable.error(Throwable("Error saving item")))

        var result = addItem.execute().test()
        scheduler.triggerActions()

        result.assertValue { it is Result.Error}
        result.assertNotComplete()
    }
}