package seph.android.com.itemtracker.view.ui

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import seph.android.com.itemtracker.R
import seph.android.com.itemtracker.view.adapter.ItemsAdapter
import seph.android.com.itemtracker.view.ui.base.BaseActivity
import seph.android.com.itemtracker.viewmodel.MainViewModel
import javax.inject.Inject

/**
 * Created by seph on 03/05/2018.
 */

class MainActivity : BaseActivity() {

    override val layoutResourceId = R.layout.activity_main

    @Inject
    lateinit var viewModel: MainViewModel

    var itemsAdapter : ItemsAdapter = ItemsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            startActivity(Intent(this, AddItemActivity::class.java))
        }

        viewModel.liveData.observe(this,  Observer(itemsAdapter::loadModels))

        // Set the adapter
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = itemsAdapter
        }

        viewModel.getItems()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
