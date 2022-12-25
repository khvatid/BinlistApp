package com.khvatid.binlistapp.data.storage.remote

import com.khvatid.binlistapp.domain.model.BinlistModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/{value}")
    suspend fun get(@Path("value") value: Int) : Response<BinlistModel>
}