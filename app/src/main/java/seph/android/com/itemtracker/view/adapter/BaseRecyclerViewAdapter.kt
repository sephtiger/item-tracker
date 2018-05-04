package seph.android.com.itemtracker.view.adapter

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by seph on 04/05/2018.
 */

abstract class BaseRecyclerViewAdapter<S ,T : RecyclerView.ViewHolder> : RecyclerView.Adapter<T>() {

    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    protected val mItems: ArrayList<S> = ArrayList()

    fun getView(parent: ViewGroup) : View {
        return LayoutInflater.from(parent.context)
                .inflate(layoutResourceId, parent, false)
    }

    fun loadModels(models : List<S>?) {
        if(models != null && !models.isEmpty()) {
            mItems.clear()
            mItems.addAll(models)
            notifyDataSetChanged()
        }
    }
}