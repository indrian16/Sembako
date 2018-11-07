package io.indrian16.sembako.ui.main.presenter

import io.indrian16.sembako.ui.main.view.MainView
import javax.inject.Inject

class MainPresenterImpl @Inject constructor(private val view: MainView) : MainPresenter {

    override fun clickBaseButton() {


    }

    override fun clickSearch() {

        view.goSearchView()
    }

    override fun clickRefresh() {

        view.refreshItem()
    }

    override fun navHome() {

        view.goHome()
    }

    override fun navScanner() {

        view.goScanner()
    }

    override fun navCashier() {

        view.goCashier()
    }

    override fun navSetting() {

        view.goSetting()
    }
}