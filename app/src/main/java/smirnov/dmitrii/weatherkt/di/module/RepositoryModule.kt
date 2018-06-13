package smirnov.dmitrii.weatherkt.di.module

import dagger.Module
import dagger.Provides
import smirnov.dmitrii.weatherkt.app.system.SchedulersProvider
import smirnov.dmitrii.weatherkt.data.repository.WeatherRepository
import smirnov.dmitrii.weatherkt.network.api.OpenWeatherMapApi
import javax.inject.Singleton

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
@Module
class RepositoryModule {
    @Provides
    @Singleton
    internal fun provideWeatherRepository(api: OpenWeatherMapApi,
                                          schedulers: SchedulersProvider) = WeatherRepository(api, schedulers)
}