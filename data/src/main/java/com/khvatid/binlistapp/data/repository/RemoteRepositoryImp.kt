package com.khvatid.binlistapp.data.repository

import com.khvatid.binlistapp.data.storage.RemoteStorage
import com.khvatid.binlistapp.domain.model.BinlistModel
import com.khvatid.binlistapp.domain.repository.RemoteRepository

class RemoteRepositoryImp(private val storage: RemoteStorage) : RemoteRepository {
    override suspend fun get(value: Int): BinlistModel? {
        return storage.get(value)
    }
}