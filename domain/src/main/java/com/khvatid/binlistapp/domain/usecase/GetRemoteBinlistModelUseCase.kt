package com.khvatid.binlistapp.domain.usecase

import com.khvatid.binlistapp.domain.model.BinlistModel
import com.khvatid.binlistapp.domain.repository.RemoteRepository

class GetRemoteBinlistModelUseCase(private val repository: RemoteRepository) {
    suspend fun execute(value: Int): BinlistModel? {
        return repository.get(value)
    }
}