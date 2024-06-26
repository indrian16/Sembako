package io.indrian16.sembako.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.indrian16.sembako.ui.createitem.CreateItemModule
import io.indrian16.sembako.ui.createitem.view.CreateItemActivity
import io.indrian16.sembako.ui.main.MainActivityModule
import io.indrian16.sembako.ui.main.MainActivityProvide
import io.indrian16.sembako.ui.main.view.MainActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class, MainActivityProvide::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [CreateItemModule::class])
    abstract fun bindCreateItemActivity(): CreateItemActivity
}