package seph.android.com.itemtracker.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by seph on 03/05/2018.
 */

data class Item(val name : String = "", val description: String = "", val image : String = "",
        val location : String = "", val cost : Int = 0) : Parcelable {

    var id : String = ""

    constructor(id: String, name : String, description: String, image : String, location : String,
            cost : Int) : this(name, description, image, location, cost) {

        this.id = id
    }

    constructor(parcel: Parcel) : this(parcel.readString(), parcel.readString(),
        parcel.readString(),parcel.readString(),parcel.readInt()) {

        id = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(image)
        parcel.writeString(location)
        parcel.writeInt(cost)
        parcel.writeString(id)
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

    override fun equals(other: Any?): Boolean {
        return other is Item && id == other?.id
    }
}