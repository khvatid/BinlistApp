package com.khvatid.binlistapp.data.storage.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.khvatid.binlistapp.data.storage.room.dao.BinlistDao
import com.khvatid.binlistapp.data.storage.room.entity.BinlistEntity

@androidx.room.Database(entities = [(BinlistEntity::class)], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun binlistDao(): BinlistDao

    companion object {
        private var INSTANCE: Database? = null

        fun getInstance(context: Context): Database {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        Database::class.java,
                        "binlist_database"
                    ).fallbackToDestructiveMigration().allowMainThreadQueries()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }


}