package io.indrian16.sembako.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import io.indrian16.sembako.database.repository.sembako.Sembako
import io.indrian16.sembako.database.repository.sembako.SembakoDAO
import io.indrian16.sembako.util.AppConstant

@Database(entities = [Sembako::class], version = AppConstant.APP_DB_VER)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun sembakoDao(): SembakoDAO
}