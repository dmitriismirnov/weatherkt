package smirnov.dmitrii.weatherkt.presentation.main

import com.arellomobile.mvp.InjectViewState
import smirnov.dmitrii.weatherkt.data.interaction.WeatherInteractor
import smirnov.dmitrii.weatherkt.presentation.base.BasePresenter
import javax.inject.Inject

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
@InjectViewState
class MainPresenter @Inject constructor(private val weatherInteractor: WeatherInteractor) : BasePresenter<MainView>() {

    override fun onFirstViewAttach() {
        viewState.startDetailedWeather()
    }

    fun showCurrent() {
        viewState.startDetailedWeather()
    }

    fun showSearchCity() {
        viewState.showSearchCity()
    }

    fun showMap() {
        viewState.startMap()
    }

}