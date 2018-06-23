package smirnov.dmitrii.weatherkt.di.component

import dagger.Component
import smirnov.dmitrii.weatherkt.di.module.MainModule
import smirnov.dmitrii.weatherkt.di.scope.PerActivity
import smirnov.dmitrii.weatherkt.presentation.main.MainActivity
import smirnov.dmitrii.weatherkt.presentation.screens.details.DetailsFragment
import smirnov.dmitrii.weatherkt.presentation.screens.details.DetailsPresenter

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
@PerActivity
@Component(dependencies = [AppComponent::class], modules = [MainModule::class])
interface MainComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(detailsFragment: DetailsFragment)
}