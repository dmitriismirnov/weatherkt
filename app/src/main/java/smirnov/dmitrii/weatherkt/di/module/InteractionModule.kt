package smirnov.dmitrii.weatherkt.di.module

import dagger.Binds
import dagger.Module
import smirnov.dmitrii.weatherkt.data.interaction.WeatherInteractor
import smirnov.dmitrii.weatherkt.data.interaction.WeatherInteractorImpl

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
@Module
abstract class InteractionModule {
    @Binds
    abstract fun bindWeatherInteractor(weatherInteractor: WeatherInteractorImpl): WeatherInteractor
}