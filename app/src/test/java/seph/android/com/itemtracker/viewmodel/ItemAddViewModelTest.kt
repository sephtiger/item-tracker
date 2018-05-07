package seph.android.com.itemtracker.viewmodel

import android.net.Uri
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import seph.android.com.itemtracker.data.interactor.AddItem

/**
 * Created by seph on 04/05/2018.
 */
class ItemAddViewModelTest {

    @Mock
    lateinit var addItem: AddItem

    lateinit var itemAddViewModel: ItemAddViewModel

    @Mock
    lateinit var imageUri: Uri

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        itemAddViewModel = ItemAddViewModel(addItem)
    }

    @Test
    fun addItem() {

        // todo
    }
}