package com.khvatid.binlistapp.di

import android.content.Context
import androidx.room.Room
import com.khvatid.binlistapp.data.repository.DatabaseRepositoryImp
import com.khvatid.binlistapp.data.repository.RemoteRepositoryImp
import com.khvatid.binlistapp.data.storage.DatabaseStorage
import com.khvatid.binlistapp.data.storage.RemoteStorage
import com.khvatid.binlistapp.data.storage.remote.NetworkService
import com.khvatid.binlistapp.data.storage.remote.RemoteStorageImp
import com.khvatid.binlistapp.data.storage.room.Database
import com.khvatid.binlistapp.data.storage.room.DatabaseStorageImp
import com.khvatid.binlistapp.data.storage.room.dao.BinlistDao
import com.khvatid.binlistapp.domain.repository.DatabaseRepository
import com.khvatid.binlistapp.domain.repository.RemoteRepository
import com.khvatid.binlistapp.domain.usecase.SaveBinlistToDatabaseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideNetworkService(): NetworkService {
        return NetworkService()
    }

    @Provides
    fun provideRemoteStorage(service: NetworkService): RemoteStorage {
        return RemoteStorageImp(service = service)
    }

    @Provides
    fun provideRemoteRepository(storage: RemoteStorage): RemoteRepository {
        return RemoteRepositoryImp(storage)
    }

    @Provides
    fun provideBinlistDao(@ApplicationContext context: Context): BinlistDao {
        return Database.getInstance(context).binlistDao()
    }


    @Provides
    fun provideDatabaseStorage(binlistDao: BinlistDao): DatabaseStorage {
        return DatabaseStorageImp(binlistDao)
    }

    @Provides
    fun provideDatabaseRepository(storage: DatabaseStorage): DatabaseRepository {
        return DatabaseRepositoryImp(storage = storage)
    }


}