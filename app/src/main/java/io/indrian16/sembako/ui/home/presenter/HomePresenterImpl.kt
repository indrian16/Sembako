package io.indrian16.sembako.ui.home.presenter

import io.indrian16.sembako.database.repository.sembako.SembakoRepository
import io.indrian16.sembako.ui.home.view.HomeView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomePresenterImpl @Inject constructor(private val view: HomeView,
                                            private val repository: SembakoRepository) : HomePresenter {

    private var subscription: Disposable? = null

    override fun loadSembakoItem() {

        subscription = repository.getAllSembako()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        {sembakoList ->

                            if (!sembakoList.isEmpty()) {

                                view.addSembakoItem(sembakoList)
                            } else {

                                view.sembakoNoItem()
                            }
                        },
                        { error -> view.showError(error) }
                )
    }

    override fun unSubscribe() {

        subscription?.dispose()
    }
}