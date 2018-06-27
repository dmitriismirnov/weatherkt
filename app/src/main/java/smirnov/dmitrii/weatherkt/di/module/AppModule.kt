package smirnov.dmitrii.weatherkt.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import smirnov.dmitrii.weatherkt.app.system.AppSchedulers
import smirnov.dmitrii.weatherkt.app.system.SchedulersProvider
import javax.inject.Singleton

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
@Module
class AppModule(private val app: Application) {
    @Provides
    @Singleton
    internal fun provideAppContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulersProvider = AppSchedulers()

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(app.applicationContext)
}