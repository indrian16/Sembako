package io.indrian16.sembako.ui.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.indrian16.sembako.ui.home.HomeModule
import io.indrian16.sembako.ui.home.view.HomeFragment
import io.indrian16.sembako.ui.scanner.ScannerModule
import io.indrian16.sembako.ui.scanner.view.ScannerFragment

@Module
abstract class MainActivityProvide {

    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun provideHomeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = [ScannerModule::class])
    abstract fun provideScannerFragment(): ScannerFragment
}