package smirnov.dmitrii.weatherkt.app

import android.app.Application
import android.content.Context
import smirnov.dmitrii.weatherkt.di.component.AppComponent
import smirnov.dmitrii.weatherkt.di.component.DaggerAppComponent
import smirnov.dmitrii.weatherkt.di.module.AppModule
import smirnov.dmitrii.weatherkt.di.module.NetworkModule

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        appContext = applicationContext
        initInjector()
    }

    private fun initInjector() {
        appComponent.inject(this)
    }

}