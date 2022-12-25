package com.khvatid.binlistapp.data.storage

import com.khvatid.binlistapp.domain.model.BinlistModel

interface RemoteStorage {
    suspend fun get(value: Int): BinlistModel?
}