package io.indrian16.sembako.ui.createitem.view

import io.reactivex.Observable

interface CreateItemView {

    fun barcodeChange(): Observable<CharSequence>
    fun titleChange(): Observable<CharSequence>
    fun priceChange(): Observable<CharSequence>
    fun stockChange(): Observable<CharSequence>

    fun showError(throwable: Throwable)

    fun showBarcodeError(error: String)
    fun showTitleError(error: String)
    fun showPriceError(error: String)
    fun showStockError(error: String)

    fun hideErrorBarcode()
    fun hideErrorTitle()
    fun hideErrorPrice()
    fun hideErrorStock()

    fun enableCreateButton()
    fun disableCreateButton()
}