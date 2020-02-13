package com.example.contactapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class],version = 2,exportSchema = false)
abstract class ContactDatabase:RoomDatabase(){
    abstract  fun contactDao(): ContactDAO

    companion object {
        private var instance: ContactDatabase? = null
        fun getInstance(context: Context): ContactDatabase?{
            if (instance == null){

                instance = Room.databaseBuilder(
                    context,
                    ContactDatabase::class.java, "contact-db"
                ).fallbackToDestructiveMigration().build()
            }
            return instance
        }





        fun destroyInstance(){
            instance = null
        }
    }



}

