package seph.android.com.itemtracker.viewmodel

import android.net.Uri
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import seph.android.com.itemtracker.data.interactor.AddItem
import seph.android.com.itemtracker.data.interactor.EditItem

/**
 * Created by seph on 04/05/2018.
 */
class ItemAddViewModelTest {

    @Mock
    lateinit var addItem: AddItem

    @Mock
    lateinit var editItem: EditItem

    lateinit var itemAddViewModel: ItemAddViewModel

    @Mock
    lateinit var imageUri: Uri

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        itemAddViewModel = ItemAddViewModel(addItem, editItem)
    }

    @Test
    fun addItem() {

        // todo
    }
}