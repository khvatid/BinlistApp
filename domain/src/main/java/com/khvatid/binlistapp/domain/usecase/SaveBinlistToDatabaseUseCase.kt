package com.khvatid.binlistapp.domain.usecase

import com.khvatid.binlistapp.domain.model.BinlistModel
import com.khvatid.binlistapp.domain.repository.DatabaseRepository

class SaveBinlistToDatabaseUseCase(private val repository: DatabaseRepository) {
    suspend fun execute(key: String, model: BinlistModel?) {
        repository.save(key = key, model = model)
    }
}