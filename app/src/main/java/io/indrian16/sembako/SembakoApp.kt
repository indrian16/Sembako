package io.indrian16.sembako

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.indrian16.sembako.di.component.DaggerAppComponent
import io.indrian16.sembako.di.module.AppModule
import javax.inject.Inject

/**
 *  @Write by rg16( Indrian )
 *  Create at 10 / 29 / 2018 Tenggarong East Kalimantan
 *  Last update 10 30 / 2018 Tenggarong East Kalimantan
 * */

class SembakoApp : Application(), HasActivityInjector {

    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
                .application(this)
                .appModule(AppModule())
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {

        return dispatchingAndroidInjector
    }
}