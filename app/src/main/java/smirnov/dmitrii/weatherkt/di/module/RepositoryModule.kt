package smirnov.dmitrii.weatherkt.di.module

import dagger.Binds
import dagger.Module
import smirnov.dmitrii.weatherkt.data.repository.WeatherRepository
import smirnov.dmitrii.weatherkt.data.repository.WeatherRepositoryImpl
import javax.inject.Singleton

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
@Module
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindWeatherRepository(weatherRepository: WeatherRepositoryImpl) : WeatherRepository
}