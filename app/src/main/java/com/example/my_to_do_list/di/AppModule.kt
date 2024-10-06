package com.example.my_to_do_list.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.my_to_do_list.Application
import com.example.my_to_do_list.data.local.dao.ToDoDao
import com.example.my_to_do_list.data.local.database.ToDoDatabase
import com.example.my_to_do_list.data.local.repository.RepositoryImpl
import com.example.my_to_do_list.domain.repository.Repository
import com.example.my_to_do_list.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun provideDatabase(@ApplicationContext app: Context): ToDoDatabase {
    return Room.databaseBuilder(
      app,
      ToDoDatabase::class.java,
      Constants.DATABASE_NAME
    ).build()
  }

  @Provides
  @Singleton
  fun provideDao(db: ToDoDatabase): ToDoDao {
    return db.dao()
  }

  @Provides
  @Singleton
  fun provideRepository(toDoDao: ToDoDao): Repository {
    return RepositoryImpl(toDoDao)
  }

}