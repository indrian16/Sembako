package io.indrian16.sembako.ui.home.view

import io.indrian16.sembako.database.repository.sembako.Sembako

interface HomeView {

    fun addSembakoItem(sembakoList: List<Sembako>)

    fun sembakoNoItem()

    fun showError(error: Throwable)
}