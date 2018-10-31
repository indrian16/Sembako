package io.indrian16.sembako.ui.scanner.presenter

import io.indrian16.sembako.ui.scanner.view.ScannerView
import javax.inject.Inject

class ScannerPresenterImpl @Inject constructor(private val view: ScannerView) : ScannerPresenter {

    override fun onFlashLight() {

        view.setOnFlashLight(true)
    }

    override fun offFlashLight() {

        view.setOffFlashLight(false)
    }

    override fun onAutoFocus() {

        view.setOnAutoFocus(true)
    }

    override fun offAutoFocus() {

        view.setOffAutoFocus(false)
    }
}