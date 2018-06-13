package smirnov.dmitrii.weatherkt.presentation.main

import smirnov.dmitrii.weatherkt.entity.openweathermap.CurrentWeather

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
interface MainView {
    fun initialiseView()
    fun showProgress(show: Boolean)
    fun showError(message: String)
    fun showLocationWeather(weather: CurrentWeather)
}