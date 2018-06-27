package smirnov.dmitrii.weatherkt.di.module

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import smirnov.dmitrii.weatherkt.data.interaction.WeatherInteractor
import smirnov.dmitrii.weatherkt.presentation.main.MainPresenter
import smirnov.dmitrii.weatherkt.presentation.screens.details.DetailsPresenter
import smirnov.dmitrii.weatherkt.presentation.screens.map.WeatherMapPresenter
import javax.inject.Singleton

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
@Module
class MainModule {
    @Singleton
    @Provides
    internal fun provideMainPresenter(weatherInteractor: WeatherInteractor) = MainPresenter(weatherInteractor)

    @Singleton
    @Provides
    internal fun provideDetailsPresenter(weatherInteractor: WeatherInteractor,
                                         sharedPreferences: SharedPreferences) = DetailsPresenter(weatherInteractor, sharedPreferences)

    @Singleton
    @Provides
    internal fun provideWeatherMapPresenter(weatherInteractor: WeatherInteractor,
                                         sharedPreferences: SharedPreferences) = WeatherMapPresenter(weatherInteractor, sharedPreferences)
}