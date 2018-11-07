package io.indrian16.sembako.ui.scanner

import dagger.Module
import dagger.Provides
import io.indrian16.sembako.ui.scanner.presenter.ScannerPresenter
import io.indrian16.sembako.ui.scanner.presenter.ScannerPresenterImpl
import io.indrian16.sembako.ui.scanner.view.ScannerFragment
import io.indrian16.sembako.ui.scanner.view.ScannerView

@Module
class ScannerModule {

    @Provides
    fun provideScannerView(scannerFragment: ScannerFragment): ScannerView = scannerFragment

    @Provides
    fun provideScannerPresenter(scannerView: ScannerView): ScannerPresenter =

            ScannerPresenterImpl(scannerView)
}