package com.khvatid.binlistapp.data.storage.room

import com.khvatid.binlistapp.data.storage.DatabaseStorage
import com.khvatid.binlistapp.data.storage.room.dao.BinlistDao
import com.khvatid.binlistapp.data.storage.room.entity.BinlistEntity

class DatabaseStorageImp(private val binlistDao: BinlistDao) : DatabaseStorage {



    override fun getOne(id: Int): BinlistEntity {
        return binlistDao.getOne(id)
    }

    override fun insert(binlistEntity: BinlistEntity) {
        binlistDao.insert(binlistEntity)
    }

    override fun getAll(): List<BinlistEntity> {
        return binlistDao.getAll()
    }

    override fun deleteOne(id: Int) {
        binlistDao.deleteOne(id)
    }

    override fun deleteAll() {
        binlistDao.deleteAll()
    }
}