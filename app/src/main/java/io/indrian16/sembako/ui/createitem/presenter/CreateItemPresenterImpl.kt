package io.indrian16.sembako.ui.createitem.presenter

import io.indrian16.sembako.database.repository.sembako.Sembako
import io.indrian16.sembako.database.repository.sembako.SembakoRepository
import io.indrian16.sembako.ui.createitem.view.CreateItemView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CreateItemPresenterImpl @Inject constructor(private val view: CreateItemView,
                                                  private val repository: SembakoRepository) : CreateItemPresenter {

    companion object {

        const val REQUIRED = "Required"
    }

    private lateinit var disposable: Disposable

    override fun insertItem(sembako: Sembako) {

        repository.insertSembako(sembako)
    }

    override fun startValidation() {

        val barcodeObservable = view.barcodeChange()
                .doOnNext { validateBarcode(it) }
        val titleObservable = view.titleChange()
                .doOnNext { validateTitle(it) }
        val priceObservable = view.priceChange()
                .doOnNext { validatePrice(it) }
        val stockObservable = view.stockChange()
                .doOnNext { validateStock(it) }

        disposable = Observables.combineLatest(

                barcodeObservable, titleObservable, priceObservable, stockObservable
        ) {

            b, t, p, s ->

            validateBarcode(b) && validateTitle(t) && validatePrice(p) && validateStock(s)
        }
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        {

                            if (it) {

                                view.enableCreateButton()

                            } else {

                                view.disableCreateButton()
                            }
                        },
                        { view.showError(it) }
                )
    }

    override fun validateBarcode(barcode: CharSequence): Boolean {

        var valid = true

        when {

            barcode.isEmpty() -> {

                view.showBarcodeError(REQUIRED)
                valid = false
            }

            else -> view.hideErrorBarcode()
        }

        return valid
    }

    override fun validateTitle(title: CharSequence): Boolean {

        var valid = true

        when {

            title.isEmpty() -> {

                view.showTitleError(REQUIRED)
                valid = false
            }

            else -> view.hideErrorTitle()
        }

        return valid
    }

    override fun validatePrice(price: CharSequence): Boolean {

        var valid = true

        when {

            price.isEmpty() -> {

                view.showPriceError(REQUIRED)
                valid = false
            }

            else -> view.hideErrorPrice()
        }

        return valid
    }

    override fun validateStock(stock: CharSequence): Boolean {

        var valid = true

        when {

            stock.isEmpty() -> {

                view.showStockError(REQUIRED)
                valid = false
            }

            else -> view.hideErrorStock()
        }

        return valid
    }

    override fun stopValidation() {

        if (disposable.isDisposed) {

            disposable.dispose()
        }
    }
}