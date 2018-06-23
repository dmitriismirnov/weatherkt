package smirnov.dmitrii.weatherkt.presentation.screens.map

import com.arellomobile.mvp.MvpView
import com.google.android.gms.maps.OnMapReadyCallback
import smirnov.dmitrii.weatherkt.entity.openweathermap.CurrentWeather

/**
 * @author Дмитрий
 * @version 23.06.2018.
 */
interface WeatherMapView : MvpView, OnMapReadyCallback {
    fun showProgress(isRefreshing: Boolean)
    fun showWeather(currentWeather: CurrentWeather)
    fun showError(error: Throwable)
}