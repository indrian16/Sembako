package io.indrian16.sembako.database.repository.sembako

import io.reactivex.Flowable

interface SembakoRepository {

    fun insertSembako(sembako: Sembako)

    fun getAllSembako(): Flowable<List<Sembako>>
}