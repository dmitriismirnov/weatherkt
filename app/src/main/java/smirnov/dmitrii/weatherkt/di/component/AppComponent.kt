package smirnov.dmitrii.weatherkt.di.component

import android.content.Context
import dagger.Component
import smirnov.dmitrii.weatherkt.app.App
import smirnov.dmitrii.weatherkt.data.interaction.WeatherInteractor
import smirnov.dmitrii.weatherkt.di.module.AppModule
import smirnov.dmitrii.weatherkt.di.module.InteractionModule
import smirnov.dmitrii.weatherkt.di.module.NetworkModule
import smirnov.dmitrii.weatherkt.di.module.RepositoryModule
import smirnov.dmitrii.weatherkt.network.api.OpenWeatherMapApi
import smirnov.dmitrii.weatherkt.presentation.screens.details.DetailsPresenter
import javax.inject.Singleton

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, RepositoryModule::class, InteractionModule::class])
interface AppComponent {
    fun inject(app: App)
    fun getAppContext(): Context

    fun getOpenMapApi(): OpenWeatherMapApi
    fun getWeatherInteractor() : WeatherInteractor
}