package com.khvatid.binlistapp.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.khvatid.binlistapp.data.storage.room.entity.BinlistEntity

@Dao
interface BinlistDao {

    @Insert
    fun insert(binlist: BinlistEntity)

    @Query("SELECT * FROM binlist WHERE id = :id")
    fun getOne(id: Int): BinlistEntity

    @Query("SELECT * FROM binlist")
    fun getAll():List<BinlistEntity>

    @Query("DELETE FROM binlist WHERE id = :id")
    fun deleteOne(id: Int)

    @Query("DELETE FROM binlist")
    fun deleteAll()
}