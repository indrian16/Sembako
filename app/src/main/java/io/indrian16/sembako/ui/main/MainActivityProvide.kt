package io.indrian16.sembako.ui.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.indrian16.sembako.ui.home.HomeModule
import io.indrian16.sembako.ui.home.view.HomeFragment

@Module
abstract class MainActivityProvide {

    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun provideHomeModule(): HomeFragment
}