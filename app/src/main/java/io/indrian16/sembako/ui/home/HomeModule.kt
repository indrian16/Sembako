package io.indrian16.sembako.ui.home

import dagger.Module
import dagger.Provides
import io.indrian16.sembako.database.repository.sembako.SembakoRepository
import io.indrian16.sembako.ui.home.presenter.HomePresenter
import io.indrian16.sembako.ui.home.presenter.HomePresenterImpl
import io.indrian16.sembako.ui.home.view.HomeFragment
import io.indrian16.sembako.ui.home.view.HomeView

@Module
class HomeModule {

    @Provides
    fun provideHomeView(homeFragment: HomeFragment): HomeView = homeFragment

    @Provides
    fun provideHomePresenter(homeView: HomeView, repository: SembakoRepository): HomePresenter =

            HomePresenterImpl(homeView, repository)
}