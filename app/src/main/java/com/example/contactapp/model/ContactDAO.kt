package com.example.contactapp.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.contactapp.model.Contact

@Dao
interface ContactDAO {
    @Query("SELECT * FROM contacts ORDER BY uid DESC")
    fun getAll():LiveData<List<Contact>>

    @Query("SELECT * FROM contacts WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: Int): List<Contact>

    @Insert
    fun insertAll(vararg contact: Contact)

    @Query("UPDATE contacts SET images = :imageUri WHERE uid = :uid")
    fun updateContact(uid: Int, imageUri: String): Int

    @Delete
    fun delete(contact: Contact)
}