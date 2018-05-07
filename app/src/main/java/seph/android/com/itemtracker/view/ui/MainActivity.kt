package seph.android.com.itemtracker.view.ui

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import seph.android.com.itemtracker.R
import seph.android.com.itemtracker.model.Item
import seph.android.com.itemtracker.view.adapter.ItemsAdapter
import seph.android.com.itemtracker.view.ui.base.BaseActivity
import seph.android.com.itemtracker.viewmodel.MainViewModel
import javax.inject.Inject

/**
 * Created by seph on 03/05/2018.
 */

class MainActivity : BaseActivity(), ItemsAdapter.OnItemClickListener  {

    override val layoutResourceId = R.layout.activity_main

    @Inject
    lateinit var viewModel: MainViewModel

    var itemsAdapter : ItemsAdapter = ItemsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { _ ->
            startActivity(Intent(this, ItemAddActivity::class.java))
        }

        viewModel.liveData.observe(this,  Observer(itemsAdapter::loadModels))

        // Set the adapter
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = itemsAdapter
        }

        viewModel.getItems()
    }

    override fun onItemClicked(item: Item) {
        var intent = Intent(this, ItemDetailActivity::class.java)
        intent.putExtra("item", item)
        startActivity(intent)
    }
}
