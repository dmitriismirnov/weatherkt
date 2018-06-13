package smirnov.dmitrii.weatherkt.di.module

import dagger.Module
import dagger.Provides
import smirnov.dmitrii.weatherkt.data.interaction.WeatherInteractor
import smirnov.dmitrii.weatherkt.data.repository.WeatherRepository
import javax.inject.Singleton

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
@Module
class InteractionModule {
    @Provides
    @Singleton
    internal fun provideWeatherInteractor(weatherRepository: WeatherRepository): WeatherInteractor =
            WeatherInteractor(weatherRepository)
}