package io.indrian16.sembako.database.repository.sembako

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface SembakoDAO {

    @Insert
    fun insertSembako(sembako: Sembako)

    @Query("select * from `sembako-item`")
    fun getAllSembako(): Flowable<List<Sembako>>
}