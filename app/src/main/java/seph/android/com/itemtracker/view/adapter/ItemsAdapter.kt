package seph.android.com.itemtracker.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_item.view.*
import seph.android.com.itemtracker.R
import seph.android.com.itemtracker.model.Item

/**
 * Created by seph on 04/05/2018.
 */

class ItemsAdapter (var onItemClickListener : OnItemClickListener)
    : BaseRecyclerViewAdapter<Item, ItemsAdapter.ViewHolder>() {

    override val layoutResourceId = R.layout.item_item



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(getView(parent))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mItems[position]
        holder.nameView.text = item.name
        holder.descriptionView.text = item.description
        holder.costView.text =  "Costs " + item.cost
        Picasso.get()
            .load(item.image)
            .into(holder.imageView)

        with(holder.view) {
            tag = item
            setOnClickListener { onItemClickListener.onItemClicked(item) }
        }
    }

    override fun getItemCount(): Int = mItems.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val nameView : TextView = view.name
        val descriptionView : TextView = view.description
        val imageView : ImageView = view.image
        val costView : TextView = view.cost

        override fun toString(): String {
            return super.toString() + " '" + nameView.text + "'"
        }
    }

    interface OnItemClickListener {

        fun onItemClicked(item : Item)
    }
}