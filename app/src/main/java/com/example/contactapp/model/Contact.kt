package com.example.contactapp.model


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "contacts")
@Parcelize
data class Contact(

    @ColumnInfo(name = "names")
    var names:String?,
    @ColumnInfo(name = "images")
    var image:String?,
    @ColumnInfo(name = "email")
    var email:String?,
    @ColumnInfo(name = "phone_number")
    var phonenumber:String?,
    @ColumnInfo(name = "address")
    var address:String?):Parcelable

{
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)var uid:Int = 0
}





