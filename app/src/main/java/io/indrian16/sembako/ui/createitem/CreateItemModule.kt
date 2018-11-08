package io.indrian16.sembako.ui.createitem

import dagger.Module
import dagger.Provides
import io.indrian16.sembako.database.repository.sembako.SembakoRepository
import io.indrian16.sembako.ui.createitem.presenter.CreateItemPresenter
import io.indrian16.sembako.ui.createitem.presenter.CreateItemPresenterImpl
import io.indrian16.sembako.ui.createitem.view.CreateItemActivity
import io.indrian16.sembako.ui.createitem.view.CreateItemView

@Module
class CreateItemModule {

    @Provides
    fun provideCreateItemView(createItemActivity: CreateItemActivity): CreateItemView = createItemActivity

    @Provides
    fun provideCreateItemPresenter(createItemView: CreateItemView,
                                   repository: SembakoRepository): CreateItemPresenter =

            CreateItemPresenterImpl(createItemView, repository)
}