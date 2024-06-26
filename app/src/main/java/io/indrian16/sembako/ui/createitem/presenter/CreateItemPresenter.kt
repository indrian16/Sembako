package io.indrian16.sembako.ui.createitem.presenter

import io.indrian16.sembako.database.repository.sembako.Sembako

interface CreateItemPresenter {

    fun startValidation()
    fun stopValidation()

    fun validateBarcode(barcode: CharSequence): Boolean
    fun validateTitle(title: CharSequence): Boolean
    fun validatePrice(price: CharSequence): Boolean
    fun validateStock(stock: CharSequence): Boolean

    fun insertItem(sembako: Sembako)
}