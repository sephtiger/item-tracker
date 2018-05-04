package seph.android.com.itemtracker.viewmodel

import android.net.Uri
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import seph.android.com.itemtracker.data.interactor.AddItem
import junit.framework.TestCase.assertEquals
import seph.android.com.itemtracker.data.Result
import seph.android.com.itemtracker.viewmodel.base.BaseViewModel

/**
 * Created by seph on 04/05/2018.
 */
class AddItemViewModelTest {

    @Mock
    lateinit var addItem: AddItem

    lateinit var addItemViewModel: AddItemViewModel

    @Mock
    lateinit var imageUri: Uri

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        addItemViewModel = AddItemViewModel(addItem)
    }

    @Test
    fun addItem() {

        // todo
    }
}