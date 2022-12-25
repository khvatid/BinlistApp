package com.khvatid.binlistapp.data.storage

import com.khvatid.binlistapp.data.storage.room.entity.BinlistEntity

interface DatabaseStorage {
    fun getOne(id: Int): BinlistEntity
    fun insert(binlistEntity: BinlistEntity)
    fun getAll(): List<BinlistEntity>
    fun deleteOne(id: Int)
    fun deleteAll()
}