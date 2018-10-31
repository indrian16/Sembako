package io.indrian16.sembako.database.repository.sembako

import io.reactivex.Flowable
import javax.inject.Inject

class SembakoRepositoryImpl @Inject constructor(private val sembakoDAO: SembakoDAO) : SembakoRepository {

    override fun insertSembako(sembako: Sembako) {

        sembakoDAO.insertSembako(sembako)
    }

    override fun getAllSembako(): Flowable<List<Sembako>> {

        return sembakoDAO.getAllSembako()
    }
}