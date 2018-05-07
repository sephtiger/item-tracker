package seph.android.com.itemtracker.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by seph on 03/05/2018.
 */

data class Item(val name : String = "", val description: String = "", val image : String = "",
        val location : String = "", val cost : Int = 0) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readString(), parcel.readString(),
        parcel.readString(),parcel.readString(),parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(image)
        parcel.writeString(location)
        parcel.writeInt(cost)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}