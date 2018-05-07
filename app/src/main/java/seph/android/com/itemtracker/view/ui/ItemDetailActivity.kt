package seph.android.com.itemtracker.view.ui

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_detail.*
import seph.android.com.itemtracker.R
import seph.android.com.itemtracker.model.Item
import seph.android.com.itemtracker.view.ui.base.BaseActivity
import seph.android.com.itemtracker.viewmodel.ItemDetailViewModel
import seph.android.com.itemtracker.viewmodel.base.BaseViewModel
import javax.inject.Inject

class ItemDetailActivity : BaseActivity() {

    override
    val layoutResourceId = R.layout.activity_item_detail

    @Inject
    lateinit var viewModel : ItemDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)

        viewModel.liveData.value = getItemFromIntent()

        editFab.setOnClickListener { _ ->
            var intent = Intent(this, ItemAddActivity::class.java)
            intent.putExtra("item", viewModel.liveData.value)
            startActivity(intent)
            finish()
        }

        deleteFab.setOnClickListener { view ->
            Snackbar.make(view, "Delete Item?", Snackbar.LENGTH_LONG)
                    .setAction("Delete", { viewModel.delete() }).show()
        }

        viewModel.state.observe(this, Observer {
            when(it) {
                is BaseViewModel.State.Success -> {
                    Toast.makeText(this, "Succesfully deleted", Toast.LENGTH_SHORT).show()
                    finish()
                }
                is BaseViewModel.State.Error ->
                    Toast.makeText(this, "Error deleting item", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.liveData.observe(this, Observer {

            supportActionBar?.title = it?.name
            Picasso.get()
                .load(it?.image)
                .into(image)

            location.text = it?.location
            description.text = it?.description
            cost.text = "Costs " + it?.cost

        })
    }

    private fun getItemFromIntent() : Item {

        if(!intent.hasExtra("item")) {
            finish()
        }

        return intent.getParcelableExtra("item")
    }
}
