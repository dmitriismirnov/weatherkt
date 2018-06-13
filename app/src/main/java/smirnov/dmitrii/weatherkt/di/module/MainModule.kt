package smirnov.dmitrii.weatherkt.di.module

import dagger.Module
import dagger.Provides
import smirnov.dmitrii.weatherkt.data.interaction.WeatherInteractor
import smirnov.dmitrii.weatherkt.di.scope.PerActivity
import smirnov.dmitrii.weatherkt.presentation.main.MainPresenter

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
@Module
class MainModule {
    @PerActivity
    @Provides
    internal fun provideMainPresenter(weatherInteractor: WeatherInteractor) = MainPresenter(weatherInteractor)
}