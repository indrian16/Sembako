package io.indrian16.sembako.ui.scanner.view

interface ScannerView {

    fun setOnFlashLight(on: Boolean)
    fun setOffFlashLight(off: Boolean)

    fun setOnAutoFocus(on: Boolean)
    fun setOffAutoFocus(off: Boolean)
}