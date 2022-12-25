package com.khvatid.binlistapp.domain.usecase

import com.khvatid.binlistapp.domain.model.BinlistModel
import com.khvatid.binlistapp.domain.repository.DatabaseRepository

class GetAllBinlistFromDatabaseUseCase(private val repository: DatabaseRepository) {

    suspend fun execute(): Map<String, BinlistModel> {
        return repository.getMap()
    }

}