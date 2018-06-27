package smirnov.dmitrii.weatherkt.di.component

import dagger.Component
import smirnov.dmitrii.weatherkt.app.App
import smirnov.dmitrii.weatherkt.di.module.*
import smirnov.dmitrii.weatherkt.presentation.main.MainActivity
import smirnov.dmitrii.weatherkt.presentation.screens.details.DetailsFragment
import smirnov.dmitrii.weatherkt.presentation.screens.map.WeatherMapFragment
import javax.inject.Singleton

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, RepositoryModule::class, InteractionModule::class, MainModule::class])
interface AppComponent {
    fun inject(app: App)
    fun inject(mainActivity: MainActivity)
    fun inject(detailsFragment: DetailsFragment)
    fun inject(weatherMapFragment: WeatherMapFragment)

//    fun getAppContext(): Context
//    fun getOpenMapApi(): OpenWeatherMapApi
//    fun getWeatherInteractor() : WeatherInteractor
}