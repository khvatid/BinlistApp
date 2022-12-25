package com.khvatid.binlistapp.data.storage.remote

import com.khvatid.binlistapp.data.storage.RemoteStorage
import com.khvatid.binlistapp.domain.model.BinlistModel
import retrofit2.Response

class RemoteStorageImp(private val service: NetworkService) :
    RemoteStorage {

    override suspend fun get(value: Int): BinlistModel? {
        val response: Response<BinlistModel> = service.execute().get(value)
        if (response.isSuccessful) {
            return response.body()
        } else {
            return null
        }
    }
}