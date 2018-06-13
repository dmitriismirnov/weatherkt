package smirnov.dmitrii.weatherkt.app

import android.app.Application
import smirnov.dmitrii.weatherkt.di.component.AppComponent
import smirnov.dmitrii.weatherkt.di.component.DaggerAppComponent
import smirnov.dmitrii.weatherkt.di.module.AppModule

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */

class App : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        initInjector()
    }

    private fun initInjector() {
        appComponent.inject(this)
    }
}