package io.indrian16.sembako.ui.main

import dagger.Module
import dagger.Provides
import io.indrian16.sembako.ui.main.presenter.MainPresenter
import io.indrian16.sembako.ui.main.presenter.MainPresenterImpl
import io.indrian16.sembako.ui.main.view.MainActivity
import io.indrian16.sembako.ui.main.view.MainView

@Module
class MainActivityModule {

    @Provides
    fun provideMainView(mainActivity: MainActivity): MainView = mainActivity

    @Provides
    fun provideMainPresenter(mainView: MainView): MainPresenter =

            MainPresenterImpl(mainView)
}