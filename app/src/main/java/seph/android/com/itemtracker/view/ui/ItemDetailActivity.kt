package seph.android.com.itemtracker.view.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_detail.*
import seph.android.com.itemtracker.R
import seph.android.com.itemtracker.model.Item
import seph.android.com.itemtracker.view.ui.base.BaseActivity
import seph.android.com.itemtracker.viewmodel.ItemDetailViewModel
import javax.inject.Inject

class ItemDetailActivity : BaseActivity() {

    override
    val layoutResourceId = R.layout.activity_item_detail

    @Inject
    lateinit var viewModel : ItemDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)

        deleteFab.setOnClickListener { view ->
            Snackbar.make(view, "Delete Item?", Snackbar.LENGTH_LONG)
                    .setAction("Delete", null).show()
        }

        viewModel.liveData.observe(this, Observer {

            supportActionBar?.title = it?.name
            Picasso.get()
                .load(it?.image)
                .into(image)

            location.text = it?.location
            description.text = it?.description
            cost.text = "Costs " + it?.cost

        })

        viewModel.liveData.value = getItemFromIntent()
    }

    private fun getItemFromIntent() : Item {

        if(!intent.hasExtra("item")) {
            finish()
        }

        return intent.getParcelableExtra("item")
    }
}
