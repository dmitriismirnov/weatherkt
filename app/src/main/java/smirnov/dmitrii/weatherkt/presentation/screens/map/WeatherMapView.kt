package smirnov.dmitrii.weatherkt.presentation.screens.map

import com.arellomobile.mvp.MvpView
import smirnov.dmitrii.weatherkt.entity.openweathermap.CurrentWeather

/**
 * @author Дмитрий
 * @version 23.06.2018.
 */
interface WeatherMapView : MvpView {
    fun showProgress(isRefreshing: Boolean)
    fun showWeather(currentWeather: CurrentWeather)
    fun showError(error: Throwable)
}