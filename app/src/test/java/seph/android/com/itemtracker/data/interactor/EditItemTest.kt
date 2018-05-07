package seph.android.com.itemtracker.data.interactor

import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import seph.android.com.itemtracker.data.Result
import seph.android.com.itemtracker.data.source.firebase.FirebaseItemRepository

/**
 * Created by seph on 07/05/2018.
 */
class EditItemTest {

    @Mock
    lateinit var repository: FirebaseItemRepository

    lateinit var editItem: EditItem

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        editItem = EditItem(repository)
        editItem.id = "some-id"
        editItem.name = "valid name"
        editItem.description = "some valid description"
        editItem.image = "http://vali/image.url"
        editItem.location = "valid location"
        editItem.cost = 999
    }

    @Test
    fun edit_item_success() {
        Mockito.`when`(repository.editItem(
            editItem.id, editItem.name, editItem.description, editItem.image, editItem.location, editItem.cost))
            .thenReturn(Flowable.just(true))

        var result = editItem.execute().test()

        result.assertValue { it is Result.Success<*> && it.data is Boolean && (it.data as Boolean)}
        result.assertNoErrors()
        result.assertComplete()
    }

    @Test
    fun edit_item_failure() {

        Mockito.`when`(repository.editItem(
            editItem.id, editItem.name, editItem.description, editItem.image, editItem.location, editItem.cost))
            .thenReturn(Flowable.error(Throwable("Error saving item")))

        var result = editItem.execute().test()

        result.assertValue { it is Result.Error}
        result.assertNotComplete()
    }
}