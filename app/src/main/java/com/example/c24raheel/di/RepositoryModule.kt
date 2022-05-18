package com.example.c24raheel.di;

import com.example.c24raheel.repository.ListItemsRepository
import com.example.c24raheel.repositoryimpl.ListItemsRepositoryImpl
import dagger.Binds
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
public abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindListRepository(listItemsRepository: ListItemsRepositoryImpl): ListItemsRepository
}
