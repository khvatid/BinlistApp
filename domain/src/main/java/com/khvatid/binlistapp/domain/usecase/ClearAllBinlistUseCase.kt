package com.khvatid.binlistapp.domain.usecase

import com.khvatid.binlistapp.domain.repository.DatabaseRepository

class ClearBinlistUseCase(private val repository: DatabaseRepository) {
    fun execute() {
        repository.deleteAll()
    }
}