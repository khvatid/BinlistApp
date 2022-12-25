package com.khvatid.binlistapp.domain.repository

import com.khvatid.binlistapp.domain.model.BinlistModel

interface RemoteRepository {

    suspend fun get(value: Int): BinlistModel?

}