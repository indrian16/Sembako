package io.indrian16.sembako.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import io.indrian16.sembako.database.AppDatabase
import io.indrian16.sembako.database.repository.sembako.SembakoRepository
import io.indrian16.sembako.database.repository.sembako.SembakoRepositoryImpl
import io.indrian16.sembako.util.AppConstant
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase =

            Room.databaseBuilder(context, AppDatabase::class.java, AppConstant.APP_DB_NAME).build()

    @Provides
    @Singleton
    fun provideSembakoRepository(appDatabase: AppDatabase): SembakoRepository =

            SembakoRepositoryImpl(appDatabase.sembakoDao())
}