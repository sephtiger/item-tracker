package seph.android.com.itemtracker.model

/**
 * Created by seph on 03/05/2018.
 */


data class Item (
        val name : String = "",
        val description: String = "",
        val image : String = "",
        val location : String = "",
        val cost : Int = 0) {
}