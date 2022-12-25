package com.khvatid.binlistapp.domain.repository

import com.khvatid.binlistapp.domain.model.BinlistModel

interface DatabaseRepository {

    fun save(key: String, model: BinlistModel?)
    suspend fun getMap(): Map<String, BinlistModel>
    fun deleteOne(id: Int)
    fun deleteAll()
}