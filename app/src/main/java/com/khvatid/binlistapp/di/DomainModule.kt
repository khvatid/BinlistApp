package com.khvatid.binlistapp.di

import com.khvatid.binlistapp.domain.repository.DatabaseRepository
import com.khvatid.binlistapp.domain.repository.RemoteRepository
import com.khvatid.binlistapp.domain.usecase.ClearBinlistUseCase
import com.khvatid.binlistapp.domain.usecase.GetAllBinlistFromDatabaseUseCase
import com.khvatid.binlistapp.domain.usecase.GetRemoteBinlistModelUseCase
import com.khvatid.binlistapp.domain.usecase.SaveBinlistToDatabaseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetRemoteBinlistModelUseCase(repository: RemoteRepository): GetRemoteBinlistModelUseCase {
        return GetRemoteBinlistModelUseCase(repository = repository)
    }


    @Provides
    fun provideSaveBinlistToDatabaseUseCase(repository: DatabaseRepository): SaveBinlistToDatabaseUseCase {
        return SaveBinlistToDatabaseUseCase(repository = repository)
    }

    @Provides
    fun provideGetAllBinlistFromDatabaseUseCase(repository: DatabaseRepository): GetAllBinlistFromDatabaseUseCase {
        return GetAllBinlistFromDatabaseUseCase(repository)
    }

    @Provides
    fun provideClearBinlistUseCase(repository: DatabaseRepository): ClearBinlistUseCase {
        return ClearBinlistUseCase(repository)
    }
}