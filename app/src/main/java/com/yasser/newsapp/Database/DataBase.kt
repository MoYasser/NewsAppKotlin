package com.yasser.newsapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [DataModel::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {

abstract fun newsDao(): NewsDao
    companion object{
        private var INSTANCE: DataBase?= null

        fun getDatabase(context: Context): DataBase{
            val tempInstance = INSTANCE
            if(tempInstance!= null){
                return tempInstance

            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java, "newsDB"
                ).build()
                INSTANCE = instance
                return instance
            }
        }


    }
}