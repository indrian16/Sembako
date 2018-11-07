package io.indrian16.sembako.ui.createitem.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.indrian16.sembako.R
import io.indrian16.sembako.ui.createitem.presenter.CreateItemPresenter
import javax.inject.Inject

class CreateItemActivity : AppCompatActivity(), CreateItemView {

    @Inject lateinit var presenter: CreateItemPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_item)
    }
}
